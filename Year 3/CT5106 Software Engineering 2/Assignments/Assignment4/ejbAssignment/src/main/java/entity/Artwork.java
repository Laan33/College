/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

/**
 * @author cathal_lawlor
 */
@Entity
@Table(name = "artworks")
@NamedQueries(
        {
                @NamedQuery(name = "Artwork.findAll", query = "SELECT aw FROM Artwork aw")
        })
public class Artwork implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "artworkid")
    private Integer artworkid;

    @Size(max = 255)
    @Column(name = "title")
    private String title;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 255)
    @Column(name = "medium")
    private String medium;

    @Size(max = 255)
    @Column(name = "imageName")
    private String imageName;

    @JoinColumn(name = "artistid", referencedColumnName = "artistid")
    @ManyToOne
    private Artist artistid;

    public Artwork() {
    }

    public Artwork(Integer artworkid) {
        this.artworkid = artworkid;
    }

    public Artwork(Integer artworkid, String title, String description, String imageName, String medium, Artist artistid) {
        this.artworkid = artworkid;
        this.title = title;
        this.description = description;
        this.imageName = imageName;
        this.medium = medium;
        this.artistid = artistid;
    }


    public Integer getArtworkid() {
        return artworkid;
    }

    public void setArtworkid(Integer artworkid) {
        this.artworkid = artworkid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


}
