package com.example.yyb;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class YybApplicationTests {
    /*
    @Autowired
    private MyUploadService myUploadService;
    @Autowired
    private PostService postService;
    @Autowired
    private  UserService userService;

    @Test
    public void TestGetUploads(){
        List<UploadMusic> uploadMusicsByUserId = myUploadService.getUploadMusicsByUserId(1);
        System.out.println(uploadMusicsByUserId.size());
    }

    @Test
    public void contextLoads() {
        Post post = new Post("你好", "c://test", new Date());
        User user= new User("niaho", "234");
        user.setId(1);
        post.setUser(user);
       *//* Post p = new Post();
        p.setId(1);
        post.setPost(p);*//*
        postService.addPost(post);
        System.out.println(post.getId());

    }
    @Test
    public void testAddPost(){
        Post post = new Post();
        post.setTimePost(new Date());
        post.setPostContent("c://nnn");
        User user = new User();
        user.setId(1);
        post.setUser(user);
        postService.dynamicPostAdd(post);
        System.out.println(post.getId());
    }
    @Test
    public void testGetPostId(){
        Post post = postService.selPostById(25);
        post.getId();
        System.out.println(post);
    }
    @Test
    public void listUsers(){
        List<User> list = new ArrayList<>();
        User user = new User("1", "3");
        list.add(user);
        user = new User("2", "6");
        list.add(user);
        user = new User("6", "r");
        list.add(user);
        user= new User("5", "k");
        list.add(user);
        Integer result = userService.batUserAdd(list);
        System.out.println("result = " + result);
    }*/

}

