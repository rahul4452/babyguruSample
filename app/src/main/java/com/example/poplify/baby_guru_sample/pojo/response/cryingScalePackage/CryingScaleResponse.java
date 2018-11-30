package com.example.poplify.baby_guru_sample.pojo.response.cryingScalePackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CryingScaleResponse implements Serializable {

    @SerializedName("crying_scale_data")
    @Expose
    private List<CryingScaleDatum> cryingScaleData = null;
    @SerializedName("guru_tips")
    @Expose
    private GuruTips guruTips;
    @SerializedName("crying_scale_label")
    @Expose
    private CryingScaleLabel cryingScaleLabel;

    /**
     * No args constructor for use in serialization
     */
    public CryingScaleResponse() {
    }

    /**
     * @param cryingScaleLabel
     * @param guruTips
     * @param cryingScaleData
     */
    public CryingScaleResponse(List<CryingScaleDatum> cryingScaleData, GuruTips guruTips, CryingScaleLabel cryingScaleLabel) {
        super();
        this.cryingScaleData = cryingScaleData;
        this.guruTips = guruTips;
        this.cryingScaleLabel = cryingScaleLabel;
    }

    public List<CryingScaleDatum> getCryingScaleData() {
        return cryingScaleData;
    }

    public void setCryingScaleData(List<CryingScaleDatum> cryingScaleData) {
        this.cryingScaleData = cryingScaleData;
    }

    public CryingScaleResponse withCryingScaleData(List<CryingScaleDatum> cryingScaleData) {
        this.cryingScaleData = cryingScaleData;
        return this;
    }

    public GuruTips getGuruTips() {
        return guruTips;
    }

    public void setGuruTips(GuruTips guruTips) {
        this.guruTips = guruTips;
    }

    public CryingScaleResponse withGuruTips(GuruTips guruTips) {
        this.guruTips = guruTips;
        return this;
    }

    public CryingScaleLabel getCryingScaleLabel() {
        return cryingScaleLabel;
    }

    public void setCryingScaleLabel(CryingScaleLabel cryingScaleLabel) {
        this.cryingScaleLabel = cryingScaleLabel;
    }

    public CryingScaleResponse withCryingScaleLabel(CryingScaleLabel cryingScaleLabel) {
        this.cryingScaleLabel = cryingScaleLabel;
        return this;
    }

//-----------------------------------com.example ..Buttons.java-----------------------------------

    public class Buttons implements Serializable {

        @SerializedName("contact_us")
        @Expose
        private String contactUs;
        @SerializedName("no_problem")
        @Expose
        private String noProblem;

        /**
         * No args constructor for use in serialization
         */
        public Buttons() {
        }

        /**
         * @param noProblem
         * @param contactUs
         */
        public Buttons(String contactUs, String noProblem) {
            super();
            this.contactUs = contactUs;
            this.noProblem = noProblem;
        }

        public String getContactUs() {
            return contactUs;
        }

        public void setContactUs(String contactUs) {
            this.contactUs = contactUs;
        }

        public Buttons withContactUs(String contactUs) {
            this.contactUs = contactUs;
            return this;
        }

        public String getNoProblem() {
            return noProblem;
        }

        public void setNoProblem(String noProblem) {
            this.noProblem = noProblem;
        }

        public Buttons withNoProblem(String noProblem) {
            this.noProblem = noProblem;
            return this;
        }

    }

//    -----------------------------------com.example ..CryingScaleDatum.java-----------------------------------

    public class CryingScaleDatum implements Serializable {

        @SerializedName("method_one")
        @Expose
        private List<MethodOne> methodOne = null;
        @SerializedName("method_two")
        @Expose
        private List<MethodTwo> methodTwo = null;

        /**
         * No args constructor for use in serialization
         */
        public CryingScaleDatum() {
        }

        /**
         * @param methodOne
         * @param methodTwo
         */
        public CryingScaleDatum(List<MethodOne> methodOne, List<MethodTwo> methodTwo) {
            super();
            this.methodOne = methodOne;
            this.methodTwo = methodTwo;
        }

        public List<MethodOne> getMethodOne() {
            return methodOne;
        }

        public void setMethodOne(List<MethodOne> methodOne) {
            this.methodOne = methodOne;
        }

        public CryingScaleDatum withMethodOne(List<MethodOne> methodOne) {
            this.methodOne = methodOne;
            return this;
        }

        public List<MethodTwo> getMethodTwo() {
            return methodTwo;
        }

        public void setMethodTwo(List<MethodTwo> methodTwo) {
            this.methodTwo = methodTwo;
        }

        public CryingScaleDatum withMethodTwo(List<MethodTwo> methodTwo) {
            this.methodTwo = methodTwo;
            return this;
        }

    }

//    -----------------------------------com.example ..CryingScaleLabel.java-----------------------------------

    public class CryingScaleLabel implements Serializable {

        @SerializedName("header")
        @Expose
        private Header header;
        @SerializedName("labels")
        @Expose
        private Labels labels;
        @SerializedName("buttons")
        @Expose
        private Buttons buttons;

        /**
         * No args constructor for use in serialization
         */
        public CryingScaleLabel() {
        }

        /**
         * @param labels
         * @param buttons
         * @param header
         */
        public CryingScaleLabel(Header header, Labels labels, Buttons buttons) {
            super();
            this.header = header;
            this.labels = labels;
            this.buttons = buttons;
        }

        public Header getHeader() {
            return header;
        }

        public void setHeader(Header header) {
            this.header = header;
        }

        public CryingScaleLabel withHeader(Header header) {
            this.header = header;
            return this;
        }

        public Labels getLabels() {
            return labels;
        }

        public void setLabels(Labels labels) {
            this.labels = labels;
        }

        public CryingScaleLabel withLabels(Labels labels) {
            this.labels = labels;
            return this;
        }

        public Buttons getButtons() {
            return buttons;
        }

        public void setButtons(Buttons buttons) {
            this.buttons = buttons;
        }

        public CryingScaleLabel withButtons(Buttons buttons) {
            this.buttons = buttons;
            return this;
        }

    }

//    -----------------------------------com.example ..GuruTips.java-----------------------------------

    public class GuruTips implements Serializable {

        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("main_content")
        @Expose
        private List<MainContent> mainContent = null;

        /**
         * No args constructor for use in serialization
         */
        public GuruTips() {
        }

        /**
         * @param description
         * @param mainContent
         */
        public GuruTips(String description, List<MainContent> mainContent) {
            super();
            this.description = description;
            this.mainContent = mainContent;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public GuruTips withDescription(String description) {
            this.description = description;
            return this;
        }

        public List<MainContent> getMainContent() {
            return mainContent;
        }

        public void setMainContent(List<MainContent> mainContent) {
            this.mainContent = mainContent;
        }

        public GuruTips withMainContent(List<MainContent> mainContent) {
            this.mainContent = mainContent;
            return this;
        }

    }

//    -----------------------------------com.example ..Header.java-----------------------------------

    public class Header implements Serializable {

        @SerializedName("crying_scale")
        @Expose
        private String cryingScale;
        @SerializedName("guru_tips")
        @Expose
        private String guruTips;
        @SerializedName("method1")
        @Expose
        private String method1;
        @SerializedName("method2")
        @Expose
        private String method2;
        @SerializedName("sub_header")
        @Expose
        private String subHeader;

        /**
         * No args constructor for use in serialization
         */
        public Header() {
        }

        /**
         * @param subHeader
         * @param guruTips
         * @param cryingScale
         * @param method2
         * @param method1
         */
        public Header(String cryingScale, String guruTips, String method1, String method2, String subHeader) {
            super();
            this.cryingScale = cryingScale;
            this.guruTips = guruTips;
            this.method1 = method1;
            this.method2 = method2;
            this.subHeader = subHeader;
        }

        public String getCryingScale() {
            return cryingScale;
        }

        public void setCryingScale(String cryingScale) {
            this.cryingScale = cryingScale;
        }

        public Header withCryingScale(String cryingScale) {
            this.cryingScale = cryingScale;
            return this;
        }

        public String getGuruTips() {
            return guruTips;
        }

        public void setGuruTips(String guruTips) {
            this.guruTips = guruTips;
        }

        public Header withGuruTips(String guruTips) {
            this.guruTips = guruTips;
            return this;
        }

        public String getMethod1() {
            return method1;
        }

        public void setMethod1(String method1) {
            this.method1 = method1;
        }

        public Header withMethod1(String method1) {
            this.method1 = method1;
            return this;
        }

        public String getMethod2() {
            return method2;
        }

        public void setMethod2(String method2) {
            this.method2 = method2;
        }

        public Header withMethod2(String method2) {
            this.method2 = method2;
            return this;
        }

        public String getSubHeader() {
            return subHeader;
        }

        public void setSubHeader(String subHeader) {
            this.subHeader = subHeader;
        }

        public Header withSubHeader(String subHeader) {
            this.subHeader = subHeader;
            return this;
        }

    }


//    -----------------------------------com.example ..Labels.java-----------------------------------

    public class Labels implements Serializable {

        @SerializedName("suggestion")
        @Expose
        private String suggestion;
        @SerializedName("listen_to_sam")
        @Expose
        private String listenToSam;

        /**
         * No args constructor for use in serialization
         */
        public Labels() {
        }

        /**
         * @param listenToSam
         * @param suggestion
         */
        public Labels(String suggestion, String listenToSam) {
            super();
            this.suggestion = suggestion;
            this.listenToSam = listenToSam;
        }

        public String getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(String suggestion) {
            this.suggestion = suggestion;
        }

        public Labels withSuggestion(String suggestion) {
            this.suggestion = suggestion;
            return this;
        }

        public String getListenToSam() {
            return listenToSam;
        }

        public void setListenToSam(String listenToSam) {
            this.listenToSam = listenToSam;
        }

        public Labels withListenToSam(String listenToSam) {
            this.listenToSam = listenToSam;
            return this;
        }

    }

//    -----------------------------------com.example ..MainContent.java-----------------------------------

    public class MainContent implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("label")
        @Expose
        private String label;
        @SerializedName("description")
        @Expose
        private String description;

        /**
         * No args constructor for use in serialization
         */
        public MainContent() {
        }

        /**
         * @param id
         * @param description
         * @param label
         */
        public MainContent(Integer id, String label, String description) {
            super();
            this.id = id;
            this.label = label;
            this.description = description;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public MainContent withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public MainContent withLabel(String label) {
            this.label = label;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public MainContent withDescription(String description) {
            this.description = description;
            return this;
        }

    }

//    -----------------------------------com.example ..MethodOne.java-----------------------------------

    public class MethodOne implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("level")
        @Expose
        private String level;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("suggestion")
        @Expose
        private List<String> suggestion = null;

        /**
         * No args constructor for use in serialization
         */
        public MethodOne() {
        }

        /**
         * @param id
         * @param title
         * @param level
         * @param suggestion
         */
        public MethodOne(Integer id, String level, String title, List<String> suggestion) {
            super();
            this.id = id;
            this.level = level;
            this.title = title;
            this.suggestion = suggestion;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public MethodOne withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public MethodOne withLevel(String level) {
            this.level = level;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public MethodOne withTitle(String title) {
            this.title = title;
            return this;
        }

        public List<String> getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(List<String> suggestion) {
            this.suggestion = suggestion;
        }

        public MethodOne withSuggestion(List<String> suggestion) {
            this.suggestion = suggestion;
            return this;
        }

    }

//    -----------------------------------com.example ..MethodTwo.java-----------------------------------

    public class MethodTwo implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("level")
        @Expose
        private String level;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("suggestion")
        @Expose
        private List<String> suggestion = null;

        /**
         * No args constructor for use in serialization
         */
        public MethodTwo() {
        }

        /**
         * @param id
         * @param title
         * @param level
         * @param suggestion
         */
        public MethodTwo(Integer id, String level, String title, List<String> suggestion) {
            super();
            this.id = id;
            this.level = level;
            this.title = title;
            this.suggestion = suggestion;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public MethodTwo withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public MethodTwo withLevel(String level) {
            this.level = level;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public MethodTwo withTitle(String title) {
            this.title = title;
            return this;
        }

        public List<String> getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(List<String> suggestion) {
            this.suggestion = suggestion;
        }

        public MethodTwo withSuggestion(List<String> suggestion) {
            this.suggestion = suggestion;
            return this;
        }

    }
}