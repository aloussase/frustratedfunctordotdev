package io.github.aloussase.frustratedfunctordotdev;

import io.github.aloussase.frustratedfunctordotdev.posts.PostManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.stereotype.Controller
public class Controller {

    private final PostManager postManager;

    public Controller(PostManager postManager) {
        this.postManager = postManager;
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/cv")
    public String cv(Model model) {
        model.addAttribute("workEntries", WorkEntry.entries());
        model.addAttribute("educationEntries", EducationEntry.entries());
        return "cv";
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        final var posts = postManager.getPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/{postPath}")
    public String post(Model model, @PathVariable("postPath") String postPath) {
        final var post = postManager.getPost(postPath);
        model.addAttribute("post", post);
        return "post";
    }

}
