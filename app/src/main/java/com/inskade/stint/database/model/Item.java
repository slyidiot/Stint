package com.inskade.stint.database.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Item {

    @PrimaryKey  @NonNull
    public String itemID;
    public String itemCollectionID;
    public String name;
    public boolean paid;
    public boolean delivered;

    public Item(String itemID, String itemCollectionID, String name, boolean paid, boolean delivered) {
        this.itemID = itemID;
        this.itemCollectionID = itemCollectionID;
        this.name = name;
        this.paid = paid;
        this.delivered = delivered;
    }

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public static class ItemBuilder {
        private String entityID;
        private String itemCollectionID;
        private String name;
        private boolean paid;
        public boolean delivered;

        public ItemBuilder setID(String entityID) {
            this.entityID = entityID;
            return this;
        }

        public ItemBuilder setCollectionID(String itemCollectionID) {
            this.itemCollectionID = itemCollectionID;
            return this;
        }

        public ItemBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder setPaid(boolean paid) {
            this.paid = paid;
            return this;
        }

        public ItemBuilder setDelivered(boolean delivered) {
            this.delivered = delivered;
            return this;
        }

        public Item build() {
            return new Item(entityID, itemCollectionID, name, paid, delivered);
        }
    }
}
