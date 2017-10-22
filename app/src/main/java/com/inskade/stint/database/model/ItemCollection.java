package com.inskade.stint.database.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ItemCollection {

    @PrimaryKey
    public String id;
    public String itemName;
    public float individualCost;
    public boolean deliverStatus;

    public ItemCollection(String id,String itemName, float individualCost, boolean deliverStatus) {
        this.id = id;
        this.itemName = itemName;
        this.individualCost = individualCost;
        this.deliverStatus = deliverStatus;
    }

    public static ItemCollectionBuilder builder() {
        return new ItemCollectionBuilder();
    }

    public static class ItemCollectionBuilder {
        private String id;
        private String itemName;
        private float individualCost;
        public boolean deliverStatus;

        public ItemCollectionBuilder setID(String id) {
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

        public ItemCollectionBuilder setDeliverStatus(boolean deliverStatus) {
            this.deliverStatus = deliverStatus;
            return this;
        }

        public ItemCollection build() {
            return new ItemCollection(id, itemName, individualCost, deliverStatus);
        }
    }
}
