package com.example.poplify.baby_guru_sample.adapter;

import java.util.ArrayList;
import java.util.List;

public class Events {

    public static class paasArrayList {
        private ArrayList<String> childFullDescription;

        public paasArrayList(ArrayList<String> childFullDescription) {
            this.childFullDescription = childFullDescription;
        }

        public ArrayList<String> getChildFullDescription() {
            return childFullDescription;
        }
    }



}
