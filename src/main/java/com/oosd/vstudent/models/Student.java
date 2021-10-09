package com.oosd.vstudent.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "student_tags",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> likedPosts;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "comment_likes",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private List<Comment> likedComments;

    @OneToMany(mappedBy = "author")
    private List<Document> documents;

    @OneToMany(mappedBy = "host")
    private List<CarPool> carPools;

    @OneToOne
    @JoinColumn(name = "file_id")
    private DocumentStorage profilePicture;

    //constructors

    public Student() { }

    public Student(String username, String password, String email, Role role, List<Tag> tags, DocumentStorage profilePicture) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.tags = tags;
        this.profilePicture = profilePicture;
    }

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<CarPool> getCarPools() {
        return carPools;
    }

    public void setCarPools(List<CarPool> carPools) {
        this.carPools = carPools;
    }

    public List<Post> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(List<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public List<Comment> getLikedComments() {
        return likedComments;
    }

    public void setLikedComments(List<Comment> likedComments) {
        this.likedComments = likedComments;
    }

    public DocumentStorage getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(DocumentStorage profilePicture) {
        this.profilePicture = profilePicture;
    }

    //to string


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", tags=" + tags +
                ", posts=" + posts +
                ", likedPosts=" + likedPosts +
                ", likedComments=" + likedComments +
                ", documents=" + documents +
                ", carPools=" + carPools +
                ", profilePicture=" + profilePicture +
                '}';
    }
}
