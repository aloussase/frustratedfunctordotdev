package io.github.aloussase.frustratedfunctordotdev;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

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
        final var posts = List.<Post>of(
                )
                .stream()
                .map(post -> new Post(post.title(), post.date(), post.content().substring(0, Math.min(30, post.content().length()))))
                .toList();
        model.addAttribute("posts", posts);
        return "posts";
    }

}
