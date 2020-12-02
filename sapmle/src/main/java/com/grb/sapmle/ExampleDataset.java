package com.grb.sapmle;


import com.ramotion.expandingcollection.ECCardData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ExampleDataset {

    private List<ECCardData> dataset;

    public ExampleDataset() {
        dataset = new ArrayList<>(5);

        CardData item5 = new CardData();
        item5.setKey(144);
        item5.setMainBackgroundResource("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        item5.setHeadBackgroundResource("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        item5.setHeadTitle("पल्हारी ");
        item5.setPersonMessage("जैविक फसल | 1 हेक्टेयर देशी बीज ");
        item5.setPersonName("मयंक ");
        item5.setPersonPictureResource(R.drawable.ic_launcher_foreground);
        item5.setListItems(prepareCommentsArray());
        dataset.add(item5);

        CardData item4 = new CardData();
        item4.setKey(143);
        item4.setMainBackgroundResource("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
        item4.setHeadBackgroundResource("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
        item4.setHeadTitle("भूरपुर ");
        item4.setPersonMessage("जैविक फसल | 5 हेक्टेयर देशी बीज");
        item4.setPersonName("अभिनव ");
        item4.setPersonPictureResource(R.drawable.img_data_two);
        item4.setListItems(prepareCommentsArray());
        dataset.add(item4);

        CardData item3 = new CardData();
        item3.setKey(142);
        item3.setMainBackgroundResource("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        item3.setHeadBackgroundResource("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        item3.setHeadTitle("संपुर  ");
        item3.setPersonMessage("जैविक फसल | 3 हेक्टेयर देशी बीज");
        item3.setPersonName("श्याम ");
        item3.setPersonPictureResource(R.drawable.img_data_two);
        item3.setListItems(prepareCommentsArray());
        dataset.add(item3);

        CardData item2 = new CardData();
        item2.setKey(141);
        item2.setMainBackgroundResource("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
        item2.setHeadBackgroundResource("https://homepages.cae.wisc.edu/~ece533/images/baboon.png");
        item2.setHeadTitle("सूरजपुर  ");
        item2.setPersonName("विवेक  ");
        item2.setPersonMessage("जैविक फसल | 2 हेक्टेयर देशी बीज");
        item2.setListItems(prepareCommentsArray());
        item2.setPersonPictureResource(R.drawable.img_data_two);
        dataset.add(item2);

       /* CardData item1 = new CardData();
        item1.setMainBackgroundResource(R.drawable.night_life);
        item1.setHeadBackgroundResource(R.drawable.night_life_head);
        item1.setHeadTitle("Night Life");
        item1.setPersonMessage("Cur adelphis studere?");
        item1.setPersonName("Wallace Sutton");
        item1.setPersonPictureResource(R.drawable.wallace_sutton);
        item1.setListItems(prepareCommentsArray());
        dataset.add(item1);*/

    }

    public List<ECCardData> getDataset() {
        Collections.shuffle(dataset);
        return dataset;
    }

    private List<Comment> prepareCommentsArray() {
        Random random = new Random();
        List<Comment> comments = new ArrayList<>();
        ArrayList<Integer> list=new ArrayList<>();
        list.add(R.drawable.img_data_two);

        /*comments.addAll(Arrays.asList(
                new Comment(list, "अमानीगंज", "जैविक फसल | 2 हेक्टेयर देशी बीज| जैविक खेती में जरूरी मृदा में जीवांश कार्बन बढ़ाने के लिए वर्मी कम्पोस्ट यूनिट की स्थापना कराई जा रही है।", "jan 17, 2020")));
*//*                new Comment(list, "Barry Allen", "It is a cold powerdrain, sir.", "jun 1, 2015"),
                new Comment(list, "Bella Holmes", "Particle of a calm shield, control the alignment!", "sep 21, 1937"),
                new Comment(list, "Caroline Shaw", "The human kahless quickly promises the phenomenan.", "may 23, 1967"),
                new Comment(list, "Connor Graham", "Ionic cannon at the infinity room was the sensor of voyage, imitated to a dead pathway.", "sep 1, 1972"),
                new Comment(list, "Deann Hunt", "Vital particles, to the port.", "aug 13, 1995"),
                new Comment(list, "Ella Cole", "Stars fly with hypnosis at the boldly infinity room!", "nov 18, 1952"),
                new Comment(list, "Jayden Shaw", "Hypnosis, definition, and powerdrain.", "apr 1, 2013"),
                new Comment(list, "Jerry Carrol", "When the queen experiments for nowhere, all particles control reliable, cold captains.", "nov 14, 1964"),
                new Comment(list, "Lena Lukas", "When the c-beam experiments for astral city, all cosmonauts acquire remarkable, virtual lieutenant commanders.", "may 4, 1965"),
                new Comment(list, "Leonard Kim", "Starships walk with love at the cold parallel universe!", "jul 3, 1974"),
                new Comment(list, "Mark Baker", "Friendship at the bridge that is when quirky green people yell.", "dec 24, 1989")));*//*
        Collections.shuffle(comments);*/
        return comments;
    }
}
