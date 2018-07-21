package org.foo.sandbox.data.controller;

import javax.validation.Valid;

import org.foo.sandbox.data.model.Post;
import org.foo.sandbox.data.model.dto.PostDto;
import org.foo.sandbox.data.model.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String list() {
        return "redirect:/post/list";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("postForm") PostDto post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/edit";
        }
        try {
            post = postService.save(post);
            return "redirect:/post/list";
        } catch (Exception e) {
            Throwable tmp = e;
            do {
                ObjectError error = new ObjectError(tmp.getClass().getSimpleName(), tmp.getMessage());
                bindingResult.addError(error);
            } while ((tmp = tmp.getCause()) != null);
            return "post/edit";
        }
    }

    @RequestMapping({ "/post/list", "/post" })
    public String list(Model model) {
        model.addAttribute("posts", postService.findAll(0, 100, "updated", Direction.ASC));
        return "post/list";
    }

    @RequestMapping("/post/new")
    public String create(Model model) {
        model.addAttribute("postForm", new PostDto());
        return "post/edit";
    }

    @RequestMapping("/post/show/{id}")
    public String find(@PathVariable String id, Model model) {
        model.addAttribute("post", postService.findById(Long.valueOf(id)));
        return "post/show";
    }

    @RequestMapping("post/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Post post = postService.findById(Long.valueOf(id));
        model.addAttribute("postForm", post);
        return "post/edit";
    }

    @RequestMapping("/post/delete/{id}")
    public String delete(@PathVariable String id) {
        postService.deleteById(Long.valueOf(id));
        return "redirect:/post/list";
    }
}
