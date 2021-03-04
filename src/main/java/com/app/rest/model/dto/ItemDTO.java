package com.app.rest.model.dto;

import com.app.rest.model.persistence.ItemDetail;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ItemDTO {

    private Long id;
    private String name;
    private ItemType type;
    private String description;
    private boolean deleted;

    public ItemDTO(String name, String type, String description, boolean deleted) {
        this.name = name;
        this.type = ItemType.fromString(type);
        this.description = description;
        this.deleted = deleted;
    }
    public ItemDTO(ItemDetail itemDetail) {
        this.id = itemDetail.getId();
        this.name = itemDetail.getName();
        this.description = itemDetail.getDescription();
        this.deleted = itemDetail.isDeleted();
        try {   //ACA NO SE SI ES NECESARIO ESTE TRY-CATCH, pasa q en el order vi que lo hiciste con OPTIONAL y sino tira una excepcion, y esto entiendo seria la manera sin usar esa manera java8 de programar.
            this.type = ItemType.fromString(itemDetail.getType());
        }catch (IllegalArgumentException ia) {
            throw new IllegalArgumentException(String.format("Invalid type [%s]", itemDetail.getType())); //CREO QUE NUNCA RECUPERARIAMOS DE LA BASE UN TIPO INVALIDO, PERO ESTA POR COHERENCIA CON EL ORDER
        }

     }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type.toString();
    }

    public String getDescription() {
        return description;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                '}';
    }
    public void validate (){
        if (this.name == null || this.description == null){ //type boolean cant be null, when null->false, which is ok here.
            throw new IllegalArgumentException(String.format("Missing Fields on request"));
        }
        if (this.type == null) {
            throw new IllegalArgumentException(String.format("Value for type missing or incorrect(non-existent)"));
        }

    }
}
