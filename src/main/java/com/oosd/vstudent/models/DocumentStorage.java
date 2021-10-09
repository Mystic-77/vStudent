package com.oosd.vstudent.models;

import javax.persistence.*;
import java.io.File;

@Table(name = "document_storage")
@Entity
public class DocumentStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    int id;


    @Column(name = "file",
            columnDefinition = "mediumblob")
    private File file;

//    @OneToOne(mappedBy = "documentStorage")
//    private Document document;
//    uni directional mapping so excluding the above two lines

    DocumentStorage(){}

    public DocumentStorage(File file) {
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "DocumentStorage{" +
                "id=" + id +
                ", file=" + file +
                '}';
    }
}
