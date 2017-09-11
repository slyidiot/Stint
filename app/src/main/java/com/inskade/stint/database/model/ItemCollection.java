package com.inskade.stint.database.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ItemCollection {

    @PrimaryKey
    public int id;
    public String itemName;
    public float individualCost;

    public ItemCollection(int id,String itemName, float individualCost) {
        this.id = id;
        this.itemName = itemName;
        this.individualCost = individualCost;
    }

    public static ItemCollectionBuilder builder() {
        return new ItemCollectionBuilder();
    }

    public static class ItemCollectionBuilder {
        private int id;
        private String itemName;
        private float individualCost;

        public ItemCollectionBuilder setID(int id) {
            this.id = id;
            return this;
        }

        public ItemCollectionBuilder setName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public ItemCollectionBuilder setIndividualCost(float individualCost) {
            this.individualCost = individualCost;
            return this;
        }

        public ItemCollection build() {
            return new ItemCollection(id, itemName, individualCost);
        }
    }
}