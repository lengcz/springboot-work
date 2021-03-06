package com.it2.springbootmybatisplus;



import com.alibaba.fastjson.JSONObject;
import com.it2.springbootmybatisplus.mapper.BookMapper;
import com.it2.springbootmybatisplus.pojo.Book;
import com.it2.springbootmybatisplus.service.IUserService;
import org.apache.http.HttpHost;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {



    private RestHighLevelClient client;

    /**
     * 创建连接
     */
    @BeforeEach
    void setUp() {
        HttpHost host = HttpHost.create("http://localhost:9200");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);
    }

    /**
     * 关闭连接
     * @throws IOException
     */
    @AfterEach
    void tearDown() throws IOException {
        client.close();
    }

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    void testCreateIndex() throws IOException {
        CreateIndexRequest request=new CreateIndexRequest("books");
        client.indices().create(request,RequestOptions.DEFAULT);
    }

    @Test
    void testGetIndex() throws IOException {
        GetIndexRequest request=new GetIndexRequest("books");
        GetIndexResponse response=client.indices().get(request,RequestOptions.DEFAULT);
        System.out.println(response.getAliases());
        System.out.println(response.getMappings().get("books").sourceAsMap());
        System.out.println(response.getSettings());
    }

    @Test
    void testExistIndex() throws IOException {
        GetIndexRequest request=new GetIndexRequest("books");
        boolean bool=  client.indices().exists(request,RequestOptions.DEFAULT);
        System.out.println(bool);
    }

    /**
     * 删除索引
     */
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest request=new DeleteIndexRequest("books");
        client.indices().delete(request,RequestOptions.DEFAULT);
    }



    @Test
    public void testConnect() throws IOException {
        HttpHost host = HttpHost.create("http://localhost:9200");
        RestClientBuilder builder = RestClient.builder(host);
        client = new RestHighLevelClient(builder);
        client.close();
    }

    @Test
    // 使用IK分词器创建索引
    public void testCreateIndexByIK() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("books");
        // 设置请求参数
        String json = "{\n" +
                "    \"mappings\":{\n" +
                "        \"properties\":{\n" +
                "            \"id\":{\n" +
                "                \"type\":\"keyword\"\n" +
                "            },\n" +
                "            \"name\":{\n" +
                "                \"type\":\"text\",\n" +
                "                \"analyzer\":\"ik_max_word\",\n" +
                "                \"copy_to\":\"all\"\n" +
                "            },\n" +
                "            \"type\":{\n" +
                "                \"type\":\"keyword\"\n" +
                "            },\n" +
                "            \"description\":{\n" +
                "                 \"type\":\"text\",\n" +
                "                 \"analyzer\":\"ik_max_word\",\n" +
                "                 \"copy_to\":\"all\"\n" +
                "            },\n" +
                "            \"all\":{\n" +
                "                 \"type\":\"text\",\n" +
                "                 \"analyzer\":\"ik_max_word\"\n" +
                "            }\n" +
                "        }\n" +
                "    }   \n" +
                "}";
        request.source(json, XContentType.JSON);
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    @Autowired
    private BookMapper bookMapper;

    @Test
    void testCreateDoc() throws IOException {
        Book book=bookMapper.selectById(1);//查询出一条数据
        IndexRequest request=new IndexRequest("books").id(book.getId()+"");
        String json= JSONObject.toJSONString(book);
        request.source(json,XContentType.JSON);
        client.index(request,RequestOptions.DEFAULT);
    }

    @Test
    void testCreateDocs() throws IOException {
      List<Book> list=bookMapper.selectList(null);

      BulkRequest bulkRequest=new BulkRequest();
      list.forEach(book -> {
          IndexRequest request=new IndexRequest("books").id(book.getId()+"");
          String json= JSONObject.toJSONString(book);
          request.source(json,XContentType.JSON);
          bulkRequest.add(request);
      });
      client.bulk(bulkRequest,RequestOptions.DEFAULT);
    }

    @Test
    void testGetById() throws IOException {
        GetRequest request=new GetRequest("books","1");
        GetResponse response=client.get(request,RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
    }

    @Test
    void testSearch() throws IOException {
        SearchRequest request=new SearchRequest("books");
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("all","JAVA").caseInsensitive(true));// 条件
        request.source(builder);

        SearchResponse response=client.search(request,RequestOptions.DEFAULT);
        SearchHits hits=response.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }
    }

    @Test
    void testSearchCaseInsensitive() throws IOException {
        SearchRequest request=new SearchRequest("books");
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("all","java"));// 条件不区分大小写
        request.source(builder);

        SearchResponse response=client.search(request,RequestOptions.DEFAULT);
        SearchHits hits=response.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }
    }

    @Test
    void testSearchByPage() throws IOException {
        SearchRequest request=new SearchRequest("books");
        SearchSourceBuilder builder=new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("all","java")).from(3).size(3);//从符合的下标为3的开始往后3条(这里实际得到两条)
        request.source(builder);

        SearchResponse response=client.search(request,RequestOptions.DEFAULT);
        SearchHits hits=response.getHits();
        for(SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
        }
    }

    @Test
    void deleteDoc() throws IOException {
        DeleteRequest request=new DeleteRequest("books","1");
        DeleteResponse response=client.delete(request,RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }

    @Test
    void deleteDocs() throws IOException {
        DeleteByQueryRequest query=new DeleteByQueryRequest("books");
        query.setQuery(QueryBuilders.matchAllQuery());//匹配条件
        client.deleteByQuery(query,RequestOptions.DEFAULT);
    }

    @Test
    void deleteDocsByIds() throws IOException {
        BulkRequest bulkRequest=new BulkRequest("books");

        List<Integer> ids= Arrays.asList(1,2);
        for(Integer id:ids){
            DeleteRequest request=new DeleteRequest();
            request.id(id.toString());
            bulkRequest.add(request);
        }
        client.bulk(bulkRequest,RequestOptions.DEFAULT);
    }


    @Test
    void refreshIndex() throws IOException {
        FlushRequest request=new FlushRequest("books");

        client.indices().flush(request,RequestOptions.DEFAULT);

    }

}
