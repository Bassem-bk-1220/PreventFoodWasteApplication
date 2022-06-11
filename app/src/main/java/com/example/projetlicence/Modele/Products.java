package com.example.projetlicence.Modele;

public class Products {
    String name_product,ingredients,nutrition,date_expiration,description_product,prix,profileImage,quantity,id_product,id_seller;
Boolean isUpdate=false;
    public Products() {
    }

    public Products(String prix, String quantity) {
        this.prix = prix;
        this.quantity = quantity;
    }

    public Products(String name_product, String ingredients, String nutrition, String date_expiration, String description_product, String prix, String profileImage, String quantity, String id_product, String id_seller) {
        this.name_product = name_product;
        this.ingredients = ingredients;
        this.nutrition = nutrition;
        this.date_expiration = date_expiration;
        this.description_product = description_product;
        this.prix = prix;
        this.profileImage = profileImage;
        this.quantity = quantity;
        this.id_product = id_product;
        this.id_seller = id_seller;
    }

    public Products(String name_product, String prix, String profileImage, String quantity, String id_product) {
        this.name_product = name_product;
        this.prix = prix;
        this.profileImage = profileImage;
        this.quantity = quantity;
        this.id_product = id_product;
    }

    public Products(String name_product, String ingredients, String nutrition, String date_expiration, String description_product, String prix, String profileImage, String quantity, String id_product, Boolean isUpdate) {
        this.name_product = name_product;
        this.ingredients = ingredients;
        this.nutrition = nutrition;
        this.date_expiration = date_expiration;
        this.description_product = description_product;
        this.prix = prix;
        this.profileImage = profileImage;
        this.quantity = quantity;
        this.id_product = id_product;
        this.isUpdate = isUpdate;
    }

    public String getId_seller() {
        return id_seller;
    }

    public void setId_seller(String id_seller) {
        this.id_seller = id_seller;
    }

    public Boolean getUpdate() {
        return isUpdate;
    }

    public void setUpdate(Boolean update) {
        isUpdate = update;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public String getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(String date_expiration) {
        this.date_expiration = date_expiration;
    }

    public String getDescription_product() {
        return description_product;
    }

    public void setDescription_product(String description_product) {
        this.description_product = description_product;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
