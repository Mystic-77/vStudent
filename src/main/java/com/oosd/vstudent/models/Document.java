package com.oosd.vstudent.models;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "document")
public class Document {

    public enum DocumentType{
        DA, QP, RES; //digital assingments, question papers , resources
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private int id;

    @Enumerated
    @Column(name = "document_type",
        columnDefinition = "smallint")
    private DocumentType documentType;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "file",
        columnDefinition = "mediumblob")
    private File file;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "student_id")
    private Student author;

    //constructor

    public Document() { }

    public Document(DocumentType documentType, String documentName, String timestamp, File file, Student author) {
        this.documentType = documentType;
        this.documentName = documentName;
        this.timestamp = timestamp;
        this.file = file;
        this.author = author;
    }

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Student getAuthor() {
        return author;
    }

    public void setAuthor(Student author) {
        this.author = author;
    }

    //to string

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", documentType=" + documentType +
                ", documentName='" + documentName + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", file=" + file +
                ", author=" + author +
                '}';
    }
}
