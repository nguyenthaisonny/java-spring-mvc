package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/create")
    public String createUser(
            @ModelAttribute("newUser") @Valid User user,
            BindingResult bindingResult,
            @RequestParam("userFile") MultipartFile file) {
        // validate
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
        }

        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setAvatar(avatar);
        user.setPassword(hashedPassword);
        user.setRole(userService.getRole(user.getRole().getName()));
        this.userService.handleCreateUser(user);

        return "redirect:/admin/user";
    }

    @GetMapping
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @GetMapping("/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        System.err.println(id);
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }

    @GetMapping("/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("newUser") User user) {
        System.err.println(user.getId() + "id");
        User currentUser = this.userService.getUserById(user.getId());
        if (currentUser != null) {
            currentUser.setAddress(user.getAddress());
            currentUser.setPhone(user.getPhone());
            currentUser.setFullName(user.getFullName());
            this.userService.handleUpdateUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        return "admin/user/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        if (currentUser != null) {
            this.userService.handleDeleteUser(id);
        }

        return "redirect:/admin/user";
    }
}
