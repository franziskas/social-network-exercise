package accepting;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

public class Posts {
    private List<Post> posts = new ArrayList<>();

    public List<String> printPosts(SocialTime printingTime) {
        return posts.stream() //
            .map(post -> post.printTimestamp(printingTime)) //
            .collect(toList());
    }

    public void addPost(Post create) {
        posts.add(create);

    }

}