package com.inskade.stint.database.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Item {

    @PrimaryKey
    public String itemID;
    public String itemCollectionID;
    public String name;
    public boolean paid;

    public Item(String itemID, String itemCollectionID, String name, boolean paid) {
        this.itemID = itemID;
        this.itemCollectionID = itemCollectionID;
        this.name = name;
        this.paid = paid;
    }

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public static class ItemBuilder {
        private String entityID;
        private String itemCollectionID;
        private String name;
        private boolean paid;

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

        public Item build() {
            return new Item(entityID, itemCollectionID, name, paid);
        }
    }
}
