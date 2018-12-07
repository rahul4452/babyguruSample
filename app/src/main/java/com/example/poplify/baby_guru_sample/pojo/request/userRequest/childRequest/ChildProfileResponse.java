package com.example.poplify.baby_guru_sample.pojo.request.userRequest.childRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ChildProfileResponse implements Serializable {

    @SerializedName("method_name")
    @Expose
    private Object methodName;
    @SerializedName("child")
    @Expose
    private Child child;
    @SerializedName("sessions")
    @Expose
    private List<Object> sessions = null;
    @SerializedName("graph_details")
    @Expose
    private GraphDetails graphDetails;
    @SerializedName("child_labels")
    @Expose
    private ChildLabels childLabels;
    @SerializedName("session_message")
    @Expose
    private String sessionMessage;
    @SerializedName("no_feedback")
    @Expose
    private String noFeedback;
    @SerializedName("sleep_coaching_details")
    @Expose
    private SleepCoachingDetails sleepCoachingDetails;
    @SerializedName("sleep_coaching_labels")
    @Expose
    private SleepCoachingLabels sleepCoachingLabels;

    /**
     * No args constructor for use in serialization
     */
    public ChildProfileResponse() {
    }

    /**
     * @param sleepCoachingLabels
     * @param child
     * @param noFeedback
     * @param sessions
     * @param sleepCoachingDetails
     * @param sessionMessage
     * @param graphDetails
     * @param methodName
     * @param childLabels
     */
    public ChildProfileResponse(Object methodName, Child child, List<Object> sessions, GraphDetails graphDetails, ChildLabels childLabels, String sessionMessage, String noFeedback, SleepCoachingDetails sleepCoachingDetails, SleepCoachingLabels sleepCoachingLabels) {
        super();
        this.methodName = methodName;
        this.child = child;
        this.sessions = sessions;
        this.graphDetails = graphDetails;
        this.childLabels = childLabels;
        this.sessionMessage = sessionMessage;
        this.noFeedback = noFeedback;
        this.sleepCoachingDetails = sleepCoachingDetails;
        this.sleepCoachingLabels = sleepCoachingLabels;
    }

    public Object getMethodName() {
        return methodName;
    }

    public void setMethodName(Object methodName) {
        this.methodName = methodName;
    }

    public ChildProfileResponse withMethodName(Object methodName) {
        this.methodName = methodName;
        return this;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public ChildProfileResponse withChild(Child child) {
        this.child = child;
        return this;
    }

    public List<Object> getSessions() {
        return sessions;
    }

    public void setSessions(List<Object> sessions) {
        this.sessions = sessions;
    }

    public ChildProfileResponse withSessions(List<Object> sessions) {
        this.sessions = sessions;
        return this;
    }

    public GraphDetails getGraphDetails() {
        return graphDetails;
    }

    public void setGraphDetails(GraphDetails graphDetails) {
        this.graphDetails = graphDetails;
    }

    public ChildProfileResponse withGraphDetails(GraphDetails graphDetails) {
        this.graphDetails = graphDetails;
        return this;
    }

    public ChildLabels getChildLabels() {
        return childLabels;
    }

    public void setChildLabels(ChildLabels childLabels) {
        this.childLabels = childLabels;
    }

    public ChildProfileResponse withChildLabels(ChildLabels childLabels) {
        this.childLabels = childLabels;
        return this;
    }

    public String getSessionMessage() {
        return sessionMessage;
    }

    public void setSessionMessage(String sessionMessage) {
        this.sessionMessage = sessionMessage;
    }

    public ChildProfileResponse withSessionMessage(String sessionMessage) {
        this.sessionMessage = sessionMessage;
        return this;
    }

    public String getNoFeedback() {
        return noFeedback;
    }

    public void setNoFeedback(String noFeedback) {
        this.noFeedback = noFeedback;
    }

    public ChildProfileResponse withNoFeedback(String noFeedback) {
        this.noFeedback = noFeedback;
        return this;
    }

    public SleepCoachingDetails getSleepCoachingDetails() {
        return sleepCoachingDetails;
    }

    public void setSleepCoachingDetails(SleepCoachingDetails sleepCoachingDetails) {
        this.sleepCoachingDetails = sleepCoachingDetails;
    }

    public ChildProfileResponse withSleepCoachingDetails(SleepCoachingDetails sleepCoachingDetails) {
        this.sleepCoachingDetails = sleepCoachingDetails;
        return this;
    }

    public SleepCoachingLabels getSleepCoachingLabels() {
        return sleepCoachingLabels;
    }

    public void setSleepCoachingLabels(SleepCoachingLabels sleepCoachingLabels) {
        this.sleepCoachingLabels = sleepCoachingLabels;
    }

    public ChildProfileResponse withSleepCoachingLabels(SleepCoachingLabels sleepCoachingLabels) {
        this.sleepCoachingLabels = sleepCoachingLabels;
        return this;
    }


//-----------------------------------com.example..All.java-----------------------------------


    public class All implements Serializable{

        @SerializedName("total_duration")
        @Expose
        private Object totalDuration;
        @SerializedName("x_label")
        @Expose
        private Object xLabel;

        /**
         * No args constructor for use in serialization
         */
        public All() {
        }

        /**
         * @param totalDuration
         * @param xLabel
         */
        public All(Object totalDuration, Object xLabel) {
            super();
            this.totalDuration = totalDuration;
            this.xLabel = xLabel;
        }

        public Object getTotalDuration() {
            return totalDuration;
        }

        public void setTotalDuration(Object totalDuration) {
            this.totalDuration = totalDuration;
        }

        public All withTotalDuration(Object totalDuration) {
            this.totalDuration = totalDuration;
            return this;
        }

        public Object getXLabel() {
            return xLabel;
        }

        public void setXLabel(Object xLabel) {
            this.xLabel = xLabel;
        }

        public All withXLabel(Object xLabel) {
            this.xLabel = xLabel;
            return this;
        }

    }

//    -----------------------------------com.example..Buttons.java-----------------------------------


    public class Buttons implements Serializable{

        @SerializedName("edit")
        @Expose
        private String edit;
        @SerializedName("graph")
        @Expose
        private String graph;
        @SerializedName("yes_btn")
        @Expose
        private String yesBtn;
        @SerializedName("no_btn")
        @Expose
        private String noBtn;
        @SerializedName("change")
        @Expose
        private String change;
        @SerializedName("select")
        @Expose
        private String select;
        @SerializedName("select_method")
        @Expose
        private String selectMethod;

        /**
         * No args constructor for use in serialization
         */
        public Buttons() {
        }

        /**
         * @param selectMethod
         * @param graph
         * @param select
         * @param change
         * @param edit
         * @param yesBtn
         * @param noBtn
         */
        public Buttons(String edit, String graph, String yesBtn, String noBtn, String change, String select, String selectMethod) {
            super();
            this.edit = edit;
            this.graph = graph;
            this.yesBtn = yesBtn;
            this.noBtn = noBtn;
            this.change = change;
            this.select = select;
            this.selectMethod = selectMethod;
        }

        public String getEdit() {
            return edit;
        }

        public void setEdit(String edit) {
            this.edit = edit;
        }

        public Buttons withEdit(String edit) {
            this.edit = edit;
            return this;
        }

        public String getGraph() {
            return graph;
        }

        public void setGraph(String graph) {
            this.graph = graph;
        }

        public Buttons withGraph(String graph) {
            this.graph = graph;
            return this;
        }

        public String getYesBtn() {
            return yesBtn;
        }

        public void setYesBtn(String yesBtn) {
            this.yesBtn = yesBtn;
        }

        public Buttons withYesBtn(String yesBtn) {
            this.yesBtn = yesBtn;
            return this;
        }

        public String getNoBtn() {
            return noBtn;
        }

        public void setNoBtn(String noBtn) {
            this.noBtn = noBtn;
        }

        public Buttons withNoBtn(String noBtn) {
            this.noBtn = noBtn;
            return this;
        }

        public String getChange() {
            return change;
        }

        public void setChange(String change) {
            this.change = change;
        }

        public Buttons withChange(String change) {
            this.change = change;
            return this;
        }

        public String getSelect() {
            return select;
        }

        public void setSelect(String select) {
            this.select = select;
        }

        public Buttons withSelect(String select) {
            this.select = select;
            return this;
        }

        public String getSelectMethod() {
            return selectMethod;
        }

        public void setSelectMethod(String selectMethod) {
            this.selectMethod = selectMethod;
        }

        public Buttons withSelectMethod(String selectMethod) {
            this.selectMethod = selectMethod;
            return this;
        }

    }

//    -----------------------------------com.example..Buttons_.java-----------------------------------


    public class Buttons_ implements Serializable{

        @SerializedName("proceed_to_go")
        @Expose
        private String proceedToGo;
        @SerializedName("start")
        @Expose
        private String start;
        @SerializedName("baby_is_asleep")
        @Expose
        private String babyIsAsleep;
        @SerializedName("crying_scale")
        @Expose
        private String cryingScale;
        @SerializedName("done")
        @Expose
        private String done;
        @SerializedName("resume_coaching")
        @Expose
        private String resumeCoaching;
        @SerializedName("stop_coaching")
        @Expose
        private String stopCoaching;
        @SerializedName("consistent")
        @Expose
        private String consistent;
        @SerializedName("inconsistent")
        @Expose
        private String inconsistent;
        @SerializedName("ok")
        @Expose
        private String ok;
        @SerializedName("no_problem")
        @Expose
        private String noProblem;
        @SerializedName("contact_us")
        @Expose
        private String contactUs;
        @SerializedName("skip")
        @Expose
        private String skip;
        @SerializedName("feedback")
        @Expose
        private String feedback;
        @SerializedName("next")
        @Expose
        private String next;

        /**
         * No args constructor for use in serialization
         */
        public Buttons_() {
        }

        /**
         * @param babyIsAsleep
         * @param proceedToGo
         * @param next
         * @param done
         * @param contactUs
         * @param skip
         * @param cryingScale
         * @param feedback
         * @param noProblem
         * @param start
         * @param stopCoaching
         * @param inconsistent
         * @param ok
         * @param consistent
         * @param resumeCoaching
         */
        public Buttons_(String proceedToGo, String start, String babyIsAsleep, String cryingScale, String done, String resumeCoaching, String stopCoaching, String consistent, String inconsistent, String ok, String noProblem, String contactUs, String skip, String feedback, String next) {
            super();
            this.proceedToGo = proceedToGo;
            this.start = start;
            this.babyIsAsleep = babyIsAsleep;
            this.cryingScale = cryingScale;
            this.done = done;
            this.resumeCoaching = resumeCoaching;
            this.stopCoaching = stopCoaching;
            this.consistent = consistent;
            this.inconsistent = inconsistent;
            this.ok = ok;
            this.noProblem = noProblem;
            this.contactUs = contactUs;
            this.skip = skip;
            this.feedback = feedback;
            this.next = next;
        }

        public String getProceedToGo() {
            return proceedToGo;
        }

        public void setProceedToGo(String proceedToGo) {
            this.proceedToGo = proceedToGo;
        }

        public Buttons_ withProceedToGo(String proceedToGo) {
            this.proceedToGo = proceedToGo;
            return this;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public Buttons_ withStart(String start) {
            this.start = start;
            return this;
        }

        public String getBabyIsAsleep() {
            return babyIsAsleep;
        }

        public void setBabyIsAsleep(String babyIsAsleep) {
            this.babyIsAsleep = babyIsAsleep;
        }

        public Buttons_ withBabyIsAsleep(String babyIsAsleep) {
            this.babyIsAsleep = babyIsAsleep;
            return this;
        }

        public String getCryingScale() {
            return cryingScale;
        }

        public void setCryingScale(String cryingScale) {
            this.cryingScale = cryingScale;
        }

        public Buttons_ withCryingScale(String cryingScale) {
            this.cryingScale = cryingScale;
            return this;
        }

        public String getDone() {
            return done;
        }

        public void setDone(String done) {
            this.done = done;
        }

        public Buttons_ withDone(String done) {
            this.done = done;
            return this;
        }

        public String getResumeCoaching() {
            return resumeCoaching;
        }

        public void setResumeCoaching(String resumeCoaching) {
            this.resumeCoaching = resumeCoaching;
        }

        public Buttons_ withResumeCoaching(String resumeCoaching) {
            this.resumeCoaching = resumeCoaching;
            return this;
        }

        public String getStopCoaching() {
            return stopCoaching;
        }

        public void setStopCoaching(String stopCoaching) {
            this.stopCoaching = stopCoaching;
        }

        public Buttons_ withStopCoaching(String stopCoaching) {
            this.stopCoaching = stopCoaching;
            return this;
        }

        public String getConsistent() {
            return consistent;
        }

        public void setConsistent(String consistent) {
            this.consistent = consistent;
        }

        public Buttons_ withConsistent(String consistent) {
            this.consistent = consistent;
            return this;
        }

        public String getInconsistent() {
            return inconsistent;
        }

        public void setInconsistent(String inconsistent) {
            this.inconsistent = inconsistent;
        }

        public Buttons_ withInconsistent(String inconsistent) {
            this.inconsistent = inconsistent;
            return this;
        }

        public String getOk() {
            return ok;
        }

        public void setOk(String ok) {
            this.ok = ok;
        }

        public Buttons_ withOk(String ok) {
            this.ok = ok;
            return this;
        }

        public String getNoProblem() {
            return noProblem;
        }

        public void setNoProblem(String noProblem) {
            this.noProblem = noProblem;
        }

        public Buttons_ withNoProblem(String noProblem) {
            this.noProblem = noProblem;
            return this;
        }

        public String getContactUs() {
            return contactUs;
        }

        public void setContactUs(String contactUs) {
            this.contactUs = contactUs;
        }

        public Buttons_ withContactUs(String contactUs) {
            this.contactUs = contactUs;
            return this;
        }

        public String getSkip() {
            return skip;
        }

        public void setSkip(String skip) {
            this.skip = skip;
        }

        public Buttons_ withSkip(String skip) {
            this.skip = skip;
            return this;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public Buttons_ withFeedback(String feedback) {
            this.feedback = feedback;
            return this;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public Buttons_ withNext(String next) {
            this.next = next;
            return this;
        }

    }

//    -----------------------------------com.example..Card.java-----------------------------------


    public class Card implements Serializable{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("details")
        @Expose
        private String details;

        /**
         * No args constructor for use in serialization
         */
        public Card() {
        }

        /**
         * @param id
         * @param details
         */
        public Card(Integer id, String details) {
            super();
            this.id = id;
            this.details = details;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Card withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public Card withDetails(String details) {
            this.details = details;
            return this;
        }

    }

//    -----------------------------------com.example..Child.java-----------------------------------


    public class Child implements Serializable{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("gender_id")
        @Expose
        private Integer genderId;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("method_id")
        @Expose
        private Object methodId;
        @SerializedName("image_updated_at")
        @Expose
        private String imageUpdatedAt;
        @SerializedName("image_file_size")
        @Expose
        private Integer imageFileSize;
        @SerializedName("image_content_type")
        @Expose
        private String imageContentType;
        @SerializedName("image_file_name")
        @Expose
        private String imageFileName;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;
        @SerializedName("gender")
        @Expose
        private List<Gender> gender = null;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("image_url")
        @Expose
        private String imageUrl;
        @SerializedName("saved_method")
        @Expose
        private Object savedMethod;
        @SerializedName("saved_method_boolean")
        @Expose
        private Boolean savedMethodBoolean;

        /**
         * No args constructor for use in serialization
         */
        public Child() {
        }

        /**
         * @param imageFileSize
         * @param savedMethodBoolean
         * @param imageUrl
         * @param genderId
         * @param imageContentType
         * @param id
         * @param imageFileName
         * @param name
         * @param userId
         * @param dob
         * @param deletedAt
         * @param imageUpdatedAt
         * @param gender
         * @param savedMethod
         * @param methodId
         */
        public Child(Integer id, String name, Integer genderId, Integer userId, Object methodId, String imageUpdatedAt, Integer imageFileSize, String imageContentType, String imageFileName, Object deletedAt, List<Gender> gender, String dob, String imageUrl, Object savedMethod, Boolean savedMethodBoolean) {
            super();
            this.id = id;
            this.name = name;
            this.genderId = genderId;
            this.userId = userId;
            this.methodId = methodId;
            this.imageUpdatedAt = imageUpdatedAt;
            this.imageFileSize = imageFileSize;
            this.imageContentType = imageContentType;
            this.imageFileName = imageFileName;
            this.deletedAt = deletedAt;
            this.gender = gender;
            this.dob = dob;
            this.imageUrl = imageUrl;
            this.savedMethod = savedMethod;
            this.savedMethodBoolean = savedMethodBoolean;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Child withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Child withName(String name) {
            this.name = name;
            return this;
        }

        public Integer getGenderId() {
            return genderId;
        }

        public void setGenderId(Integer genderId) {
            this.genderId = genderId;
        }

        public Child withGenderId(Integer genderId) {
            this.genderId = genderId;
            return this;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Child withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Object getMethodId() {
            return methodId;
        }

        public void setMethodId(Object methodId) {
            this.methodId = methodId;
        }

        public Child withMethodId(Object methodId) {
            this.methodId = methodId;
            return this;
        }

        public String getImageUpdatedAt() {
            return imageUpdatedAt;
        }

        public void setImageUpdatedAt(String imageUpdatedAt) {
            this.imageUpdatedAt = imageUpdatedAt;
        }

        public Child withImageUpdatedAt(String imageUpdatedAt) {
            this.imageUpdatedAt = imageUpdatedAt;
            return this;
        }

        public Integer getImageFileSize() {
            return imageFileSize;
        }

        public void setImageFileSize(Integer imageFileSize) {
            this.imageFileSize = imageFileSize;
        }

        public Child withImageFileSize(Integer imageFileSize) {
            this.imageFileSize = imageFileSize;
            return this;
        }

        public String getImageContentType() {
            return imageContentType;
        }

        public void setImageContentType(String imageContentType) {
            this.imageContentType = imageContentType;
        }

        public Child withImageContentType(String imageContentType) {
            this.imageContentType = imageContentType;
            return this;
        }

        public String getImageFileName() {
            return imageFileName;
        }

        public void setImageFileName(String imageFileName) {
            this.imageFileName = imageFileName;
        }

        public Child withImageFileName(String imageFileName) {
            this.imageFileName = imageFileName;
            return this;
        }

        public Object getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
        }

        public Child withDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public List<Gender> getGender() {
            return gender;
        }

        public void setGender(List<Gender> gender) {
            this.gender = gender;
        }

        public Child withGender(List<Gender> gender) {
            this.gender = gender;
            return this;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public Child withDob(String dob) {
            this.dob = dob;
            return this;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Child withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Object getSavedMethod() {
            return savedMethod;
        }

        public void setSavedMethod(Object savedMethod) {
            this.savedMethod = savedMethod;
        }

        public Child withSavedMethod(Object savedMethod) {
            this.savedMethod = savedMethod;
            return this;
        }

        public Boolean getSavedMethodBoolean() {
            return savedMethodBoolean;
        }

        public void setSavedMethodBoolean(Boolean savedMethodBoolean) {
            this.savedMethodBoolean = savedMethodBoolean;
        }

        public Child withSavedMethodBoolean(Boolean savedMethodBoolean) {
            this.savedMethodBoolean = savedMethodBoolean;
            return this;
        }

    }

//    -----------------------------------com.example..ChildLabels.java-----------------------------------


    public class ChildLabels implements Serializable{

        @SerializedName("child_profile_labels")
        @Expose
        private ChildProfileLabels childProfileLabels;
        @SerializedName("sleep_graph_labels")
        @Expose
        private SleepGraphLabels sleepGraphLabels;

        /**
         * No args constructor for use in serialization
         */
        public ChildLabels() {
        }

        /**
         * @param childProfileLabels
         * @param sleepGraphLabels
         */
        public ChildLabels(ChildProfileLabels childProfileLabels, SleepGraphLabels sleepGraphLabels) {
            super();
            this.childProfileLabels = childProfileLabels;
            this.sleepGraphLabels = sleepGraphLabels;
        }

        public ChildProfileLabels getChildProfileLabels() {
            return childProfileLabels;
        }

        public void setChildProfileLabels(ChildProfileLabels childProfileLabels) {
            this.childProfileLabels = childProfileLabels;
        }

        public ChildLabels withChildProfileLabels(ChildProfileLabels childProfileLabels) {
            this.childProfileLabels = childProfileLabels;
            return this;
        }

        public SleepGraphLabels getSleepGraphLabels() {
            return sleepGraphLabels;
        }

        public void setSleepGraphLabels(SleepGraphLabels sleepGraphLabels) {
            this.sleepGraphLabels = sleepGraphLabels;
        }

        public ChildLabels withSleepGraphLabels(SleepGraphLabels sleepGraphLabels) {
            this.sleepGraphLabels = sleepGraphLabels;
            return this;
        }

    }


    //-----------------------------------com.example..ChildProfileLabels.java-----------------------------------


    public class ChildProfileLabels implements Serializable{

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
        public ChildProfileLabels() {
        }

        /**
         * @param labels
         * @param buttons
         * @param header
         */
        public ChildProfileLabels(Header header, Labels labels, Buttons buttons) {
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

        public ChildProfileLabels withHeader(Header header) {
            this.header = header;
            return this;
        }

        public Labels getLabels() {
            return labels;
        }

        public void setLabels(Labels labels) {
            this.labels = labels;
        }

        public ChildProfileLabels withLabels(Labels labels) {
            this.labels = labels;
            return this;
        }

        public Buttons getButtons() {
            return buttons;
        }

        public void setButtons(Buttons buttons) {
            this.buttons = buttons;
        }

        public ChildProfileLabels withButtons(Buttons buttons) {
            this.buttons = buttons;
            return this;
        }

    }

    // -----------------------------------com.example..ChooseMethod.java-----------------------------------


    public class ChooseMethod implements Serializable{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("listen_to_sam")
        @Expose
        private String listenToSam;
        @SerializedName("short_description")
        @Expose
        private String shortDescription;
        @SerializedName("read_more")
        @Expose
        private String readMore;
        @SerializedName("default_name")
        @Expose
        private String defaultName;
        @SerializedName("full_description")
        @Expose
        private List<String> fullDescription = null;
        @SerializedName("cards")
        @Expose
        private List<Card> cards = null;
        @SerializedName("infinite_cards")
        @Expose
        private List<InfiniteCard> infiniteCards = null;
        @SerializedName("label")
        @Expose
        private String label;
        @SerializedName("active")
        @Expose
        private Boolean active;

        /**
         * No args constructor for use in serialization
         */
        public ChooseMethod() {
        }

        /**
         * @param defaultName
         * @param listenToSam
         * @param id
         * @param title
         * @param fullDescription
         * @param shortDescription
         * @param active
         * @param label
         * @param infiniteCards
         * @param readMore
         * @param cards
         */
        public ChooseMethod(Integer id, String title, String listenToSam, String shortDescription, String readMore, String defaultName, List<String> fullDescription, List<Card> cards, List<InfiniteCard> infiniteCards, String label, Boolean active) {
            super();
            this.id = id;
            this.title = title;
            this.listenToSam = listenToSam;
            this.shortDescription = shortDescription;
            this.readMore = readMore;
            this.defaultName = defaultName;
            this.fullDescription = fullDescription;
            this.cards = cards;
            this.infiniteCards = infiniteCards;
            this.label = label;
            this.active = active;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public ChooseMethod withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ChooseMethod withTitle(String title) {
            this.title = title;
            return this;
        }

        public String getListenToSam() {
            return listenToSam;
        }

        public void setListenToSam(String listenToSam) {
            this.listenToSam = listenToSam;
        }

        public ChooseMethod withListenToSam(String listenToSam) {
            this.listenToSam = listenToSam;
            return this;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public ChooseMethod withShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public String getReadMore() {
            return readMore;
        }

        public void setReadMore(String readMore) {
            this.readMore = readMore;
        }

        public ChooseMethod withReadMore(String readMore) {
            this.readMore = readMore;
            return this;
        }

        public String getDefaultName() {
            return defaultName;
        }

        public void setDefaultName(String defaultName) {
            this.defaultName = defaultName;
        }

        public ChooseMethod withDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public List<String> getFullDescription() {
            return fullDescription;
        }

        public void setFullDescription(List<String> fullDescription) {
            this.fullDescription = fullDescription;
        }

        public ChooseMethod withFullDescription(List<String> fullDescription) {
            this.fullDescription = fullDescription;
            return this;
        }

        public List<Card> getCards() {
            return cards;
        }

        public void setCards(List<Card> cards) {
            this.cards = cards;
        }

        public ChooseMethod withCards(List<Card> cards) {
            this.cards = cards;
            return this;
        }

        public List<InfiniteCard> getInfiniteCards() {
            return infiniteCards;
        }

        public void setInfiniteCards(List<InfiniteCard> infiniteCards) {
            this.infiniteCards = infiniteCards;
        }

        public ChooseMethod withInfiniteCards(List<InfiniteCard> infiniteCards) {
            this.infiniteCards = infiniteCards;
            return this;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public ChooseMethod withLabel(String label) {
            this.label = label;
            return this;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

        public ChooseMethod withActive(Boolean active) {
            this.active = active;
            return this;
        }

    }

    //-----------------------------------com.example..Gender.java-----------------------------------


    public class Gender implements Serializable{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("default_name")
        @Expose
        private String defaultName;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("selected")
        @Expose
        private Boolean selected;

        /**
         * No args constructor for use in serialization
         */
        public Gender() {
        }

        /**
         * @param defaultName
         * @param id
         * @param selected
         * @param name
         */
        public Gender(Integer id, String defaultName, String name, Boolean selected) {
            super();
            this.id = id;
            this.defaultName = defaultName;
            this.name = name;
            this.selected = selected;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Gender withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getDefaultName() {
            return defaultName;
        }

        public void setDefaultName(String defaultName) {
            this.defaultName = defaultName;
        }

        public Gender withDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Gender withName(String name) {
            this.name = name;
            return this;
        }

        public Boolean getSelected() {
            return selected;
        }

        public void setSelected(Boolean selected) {
            this.selected = selected;
        }

        public Gender withSelected(Boolean selected) {
            this.selected = selected;
            return this;
        }

    }

//    -----------------------------------com.example..GraphDetails.java-----------------------------------


    public class GraphDetails implements Serializable{

        @SerializedName("all")
        @Expose
        private All all;
        @SerializedName("today")
        @Expose
        private Today today;

        /**
         * No args constructor for use in serialization
         */
        public GraphDetails() {
        }

        /**
         * @param today
         * @param all
         */
        public GraphDetails(All all, Today today) {
            super();
            this.all = all;
            this.today = today;
        }

        public All getAll() {
            return all;
        }

        public void setAll(All all) {
            this.all = all;
        }

        public GraphDetails withAll(All all) {
            this.all = all;
            return this;
        }

        public Today getToday() {
            return today;
        }

        public void setToday(Today today) {
            this.today = today;
        }

        public GraphDetails withToday(Today today) {
            this.today = today;
            return this;
        }

    }

//    -----------------------------------com.example..Header.java-----------------------------------


    public class Header implements Serializable{

        @SerializedName("child_profile")
        @Expose
        private String childProfile;
        @SerializedName("sleep_graph")
        @Expose
        private String sleepGraph;

        /**
         * No args constructor for use in serialization
         */
        public Header() {
        }

        /**
         * @param sleepGraph
         * @param childProfile
         */
        public Header(String childProfile, String sleepGraph) {
            super();
            this.childProfile = childProfile;
            this.sleepGraph = sleepGraph;
        }

        public String getChildProfile() {
            return childProfile;
        }

        public void setChildProfile(String childProfile) {
            this.childProfile = childProfile;
        }

        public Header withChildProfile(String childProfile) {
            this.childProfile = childProfile;
            return this;
        }

        public String getSleepGraph() {
            return sleepGraph;
        }

        public void setSleepGraph(String sleepGraph) {
            this.sleepGraph = sleepGraph;
        }

        public Header withSleepGraph(String sleepGraph) {
            this.sleepGraph = sleepGraph;
            return this;
        }

    }


//    -----------------------------------com.example..Header_.java-----------------------------------


    public class Header_ implements Serializable{

        @SerializedName("choose_method")
        @Expose
        private String chooseMethod;
        @SerializedName("sleep_coaching")
        @Expose
        private String sleepCoaching;
        @SerializedName("guru_tips")
        @Expose
        private String guruTips;
        @SerializedName("crying_scale")
        @Expose
        private String cryingScale;
        @SerializedName("before_you_start")
        @Expose
        private String beforeYouStart;
        @SerializedName("important_info")
        @Expose
        private String importantInfo;

        /**
         * No args constructor for use in serialization
         */
        public Header_() {
        }

        /**
         * @param beforeYouStart
         * @param guruTips
         * @param sleepCoaching
         * @param cryingScale
         * @param chooseMethod
         * @param importantInfo
         */
        public Header_(String chooseMethod, String sleepCoaching, String guruTips, String cryingScale, String beforeYouStart, String importantInfo) {
            super();
            this.chooseMethod = chooseMethod;
            this.sleepCoaching = sleepCoaching;
            this.guruTips = guruTips;
            this.cryingScale = cryingScale;
            this.beforeYouStart = beforeYouStart;
            this.importantInfo = importantInfo;
        }

        public String getChooseMethod() {
            return chooseMethod;
        }

        public void setChooseMethod(String chooseMethod) {
            this.chooseMethod = chooseMethod;
        }

        public Header_ withChooseMethod(String chooseMethod) {
            this.chooseMethod = chooseMethod;
            return this;
        }

        public String getSleepCoaching() {
            return sleepCoaching;
        }

        public void setSleepCoaching(String sleepCoaching) {
            this.sleepCoaching = sleepCoaching;
        }

        public Header_ withSleepCoaching(String sleepCoaching) {
            this.sleepCoaching = sleepCoaching;
            return this;
        }

        public String getGuruTips() {
            return guruTips;
        }

        public void setGuruTips(String guruTips) {
            this.guruTips = guruTips;
        }

        public Header_ withGuruTips(String guruTips) {
            this.guruTips = guruTips;
            return this;
        }

        public String getCryingScale() {
            return cryingScale;
        }

        public void setCryingScale(String cryingScale) {
            this.cryingScale = cryingScale;
        }

        public Header_ withCryingScale(String cryingScale) {
            this.cryingScale = cryingScale;
            return this;
        }

        public String getBeforeYouStart() {
            return beforeYouStart;
        }

        public void setBeforeYouStart(String beforeYouStart) {
            this.beforeYouStart = beforeYouStart;
        }

        public Header_ withBeforeYouStart(String beforeYouStart) {
            this.beforeYouStart = beforeYouStart;
            return this;
        }

        public String getImportantInfo() {
            return importantInfo;
        }

        public void setImportantInfo(String importantInfo) {
            this.importantInfo = importantInfo;
        }

        public Header_ withImportantInfo(String importantInfo) {
            this.importantInfo = importantInfo;
            return this;
        }

    }

    //-----------------------------------com.example..InfiniteCard.java-----------------------------------


    public class InfiniteCard implements Serializable{

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("details")
        @Expose
        private String details;

        /**
         * No args constructor for use in serialization
         */
        public InfiniteCard() {
        }

        /**
         * @param id
         * @param details
         */
        public InfiniteCard(Integer id, String details) {
            super();
            this.id = id;
            this.details = details;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public InfiniteCard withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public InfiniteCard withDetails(String details) {
            this.details = details;
            return this;
        }

    }


//    -----------------------------------com.example..InitialCard.java-----------------------------------


    public class InitialCard implements Serializable{

        @SerializedName("initial_card")
        @Expose
        private String initialCard;

        /**
         * No args constructor for use in serialization
         */
        public InitialCard() {
        }

        /**
         * @param initialCard
         */
        public InitialCard(String initialCard) {
            super();
            this.initialCard = initialCard;
        }

        public String getInitialCard() {
            return initialCard;
        }

        public void setInitialCard(String initialCard) {
            this.initialCard = initialCard;
        }

        public InitialCard withInitialCard(String initialCard) {
            this.initialCard = initialCard;
            return this;
        }

    }


//    -----------------------------------com.example..Labels.java-----------------------------------


    public class Labels implements Serializable{

        @SerializedName("details")
        @Expose
        private String details;
        @SerializedName("born_on")
        @Expose
        private String bornOn;
        @SerializedName("sleep_coaching_history")
        @Expose
        private String sleepCoachingHistory;
        @SerializedName("baby_boy")
        @Expose
        private String babyBoy;
        @SerializedName("baby_girl")
        @Expose
        private String babyGirl;
        @SerializedName("session")
        @Expose
        private String session;
        @SerializedName("boy")
        @Expose
        private String boy;
        @SerializedName("girl")
        @Expose
        private String girl;
        @SerializedName("today")
        @Expose
        private String today;
        @SerializedName("baby")
        @Expose
        private String baby;
        @SerializedName("all")
        @Expose
        private String all;
        @SerializedName("total_session_duration")
        @Expose
        private String totalSessionDuration;
        @SerializedName("your_feedback")
        @Expose
        private String yourFeedback;
        @SerializedName("no_session_history")
        @Expose
        private String noSessionHistory;
        @SerializedName("bottom_label")
        @Expose
        private String bottomLabel;
        @SerializedName("add_child")
        @Expose
        private String addChild;
        @SerializedName("no_feedback")
        @Expose
        private String noFeedback;
        @SerializedName("session_delete")
        @Expose
        private String sessionDelete;
        @SerializedName("coaching_method")
        @Expose
        private String coachingMethod;
        @SerializedName("delete_session")
        @Expose
        private String deleteSession;

        /**
         * No args constructor for use in serialization
         */
        public Labels() {
        }

        /**
         * @param bornOn
         * @param bottomLabel
         * @param babyBoy
         * @param today
         * @param babyGirl
         * @param yourFeedback
         * @param deleteSession
         * @param sleepCoachingHistory
         * @param noFeedback
         * @param girl
         * @param details
         * @param session
         * @param sessionDelete
         * @param totalSessionDuration
         * @param addChild
         * @param noSessionHistory
         * @param baby
         * @param coachingMethod
         * @param boy
         * @param all
         */
        public Labels(String details, String bornOn, String sleepCoachingHistory, String babyBoy, String babyGirl, String session, String boy, String girl, String today, String baby, String all, String totalSessionDuration, String yourFeedback, String noSessionHistory, String bottomLabel, String addChild, String noFeedback, String sessionDelete, String coachingMethod, String deleteSession) {
            super();
            this.details = details;
            this.bornOn = bornOn;
            this.sleepCoachingHistory = sleepCoachingHistory;
            this.babyBoy = babyBoy;
            this.babyGirl = babyGirl;
            this.session = session;
            this.boy = boy;
            this.girl = girl;
            this.today = today;
            this.baby = baby;
            this.all = all;
            this.totalSessionDuration = totalSessionDuration;
            this.yourFeedback = yourFeedback;
            this.noSessionHistory = noSessionHistory;
            this.bottomLabel = bottomLabel;
            this.addChild = addChild;
            this.noFeedback = noFeedback;
            this.sessionDelete = sessionDelete;
            this.coachingMethod = coachingMethod;
            this.deleteSession = deleteSession;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public Labels withDetails(String details) {
            this.details = details;
            return this;
        }

        public String getBornOn() {
            return bornOn;
        }

        public void setBornOn(String bornOn) {
            this.bornOn = bornOn;
        }

        public Labels withBornOn(String bornOn) {
            this.bornOn = bornOn;
            return this;
        }

        public String getSleepCoachingHistory() {
            return sleepCoachingHistory;
        }

        public void setSleepCoachingHistory(String sleepCoachingHistory) {
            this.sleepCoachingHistory = sleepCoachingHistory;
        }

        public Labels withSleepCoachingHistory(String sleepCoachingHistory) {
            this.sleepCoachingHistory = sleepCoachingHistory;
            return this;
        }

        public String getBabyBoy() {
            return babyBoy;
        }

        public void setBabyBoy(String babyBoy) {
            this.babyBoy = babyBoy;
        }

        public Labels withBabyBoy(String babyBoy) {
            this.babyBoy = babyBoy;
            return this;
        }

        public String getBabyGirl() {
            return babyGirl;
        }

        public void setBabyGirl(String babyGirl) {
            this.babyGirl = babyGirl;
        }

        public Labels withBabyGirl(String babyGirl) {
            this.babyGirl = babyGirl;
            return this;
        }

        public String getSession() {
            return session;
        }

        public void setSession(String session) {
            this.session = session;
        }

        public Labels withSession(String session) {
            this.session = session;
            return this;
        }

        public String getBoy() {
            return boy;
        }

        public void setBoy(String boy) {
            this.boy = boy;
        }

        public Labels withBoy(String boy) {
            this.boy = boy;
            return this;
        }

        public String getGirl() {
            return girl;
        }

        public void setGirl(String girl) {
            this.girl = girl;
        }

        public Labels withGirl(String girl) {
            this.girl = girl;
            return this;
        }

        public String getToday() {
            return today;
        }

        public void setToday(String today) {
            this.today = today;
        }

        public Labels withToday(String today) {
            this.today = today;
            return this;
        }

        public String getBaby() {
            return baby;
        }

        public void setBaby(String baby) {
            this.baby = baby;
        }

        public Labels withBaby(String baby) {
            this.baby = baby;
            return this;
        }

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }

        public Labels withAll(String all) {
            this.all = all;
            return this;
        }

        public String getTotalSessionDuration() {
            return totalSessionDuration;
        }

        public void setTotalSessionDuration(String totalSessionDuration) {
            this.totalSessionDuration = totalSessionDuration;
        }

        public Labels withTotalSessionDuration(String totalSessionDuration) {
            this.totalSessionDuration = totalSessionDuration;
            return this;
        }

        public String getYourFeedback() {
            return yourFeedback;
        }

        public void setYourFeedback(String yourFeedback) {
            this.yourFeedback = yourFeedback;
        }

        public Labels withYourFeedback(String yourFeedback) {
            this.yourFeedback = yourFeedback;
            return this;
        }

        public String getNoSessionHistory() {
            return noSessionHistory;
        }

        public void setNoSessionHistory(String noSessionHistory) {
            this.noSessionHistory = noSessionHistory;
        }

        public Labels withNoSessionHistory(String noSessionHistory) {
            this.noSessionHistory = noSessionHistory;
            return this;
        }

        public String getBottomLabel() {
            return bottomLabel;
        }

        public void setBottomLabel(String bottomLabel) {
            this.bottomLabel = bottomLabel;
        }

        public Labels withBottomLabel(String bottomLabel) {
            this.bottomLabel = bottomLabel;
            return this;
        }

        public String getAddChild() {
            return addChild;
        }

        public void setAddChild(String addChild) {
            this.addChild = addChild;
        }

        public Labels withAddChild(String addChild) {
            this.addChild = addChild;
            return this;
        }

        public String getNoFeedback() {
            return noFeedback;
        }

        public void setNoFeedback(String noFeedback) {
            this.noFeedback = noFeedback;
        }

        public Labels withNoFeedback(String noFeedback) {
            this.noFeedback = noFeedback;
            return this;
        }

        public String getSessionDelete() {
            return sessionDelete;
        }

        public void setSessionDelete(String sessionDelete) {
            this.sessionDelete = sessionDelete;
        }

        public Labels withSessionDelete(String sessionDelete) {
            this.sessionDelete = sessionDelete;
            return this;
        }

        public String getCoachingMethod() {
            return coachingMethod;
        }

        public void setCoachingMethod(String coachingMethod) {
            this.coachingMethod = coachingMethod;
        }

        public Labels withCoachingMethod(String coachingMethod) {
            this.coachingMethod = coachingMethod;
            return this;
        }

        public String getDeleteSession() {
            return deleteSession;
        }

        public void setDeleteSession(String deleteSession) {
            this.deleteSession = deleteSession;
        }

        public Labels withDeleteSession(String deleteSession) {
            this.deleteSession = deleteSession;
            return this;
        }

    }
//-----------------------------------com.example..Labels_.java-----------------------------------


    public class Labels_ implements Serializable{

        @SerializedName("self_settling_timer")
        @Expose
        private String selfSettlingTimer;
        @SerializedName("total_settling_timer")
        @Expose
        private String totalSettlingTimer;

        /**
         * No args constructor for use in serialization
         */
        public Labels_() {
        }

        /**
         * @param selfSettlingTimer
         * @param totalSettlingTimer
         */
        public Labels_(String selfSettlingTimer, String totalSettlingTimer) {
            super();
            this.selfSettlingTimer = selfSettlingTimer;
            this.totalSettlingTimer = totalSettlingTimer;
        }

        public String getSelfSettlingTimer() {
            return selfSettlingTimer;
        }

        public void setSelfSettlingTimer(String selfSettlingTimer) {
            this.selfSettlingTimer = selfSettlingTimer;
        }

        public Labels_ withSelfSettlingTimer(String selfSettlingTimer) {
            this.selfSettlingTimer = selfSettlingTimer;
            return this;
        }

        public String getTotalSettlingTimer() {
            return totalSettlingTimer;
        }

        public void setTotalSettlingTimer(String totalSettlingTimer) {
            this.totalSettlingTimer = totalSettlingTimer;
        }

        public Labels_ withTotalSettlingTimer(String totalSettlingTimer) {
            this.totalSettlingTimer = totalSettlingTimer;
            return this;
        }

    }
//-----------------------------------com.example..SleepCoachingDetails.java-----------------------------------


    public class SleepCoachingDetails implements Serializable{

        @SerializedName("choose_methods")
        @Expose
        private List<ChooseMethod> chooseMethods = null;
        @SerializedName("initial_card")
        @Expose
        private InitialCard initialCard;

        /**
         * No args constructor for use in serialization
         */
        public SleepCoachingDetails() {
        }

        /**
         * @param chooseMethods
         * @param initialCard
         */
        public SleepCoachingDetails(List<ChooseMethod> chooseMethods, InitialCard initialCard) {
            super();
            this.chooseMethods = chooseMethods;
            this.initialCard = initialCard;
        }

        public List<ChooseMethod> getChooseMethods() {
            return chooseMethods;
        }

        public void setChooseMethods(List<ChooseMethod> chooseMethods) {
            this.chooseMethods = chooseMethods;
        }

        public SleepCoachingDetails withChooseMethods(List<ChooseMethod> chooseMethods) {
            this.chooseMethods = chooseMethods;
            return this;
        }

        public InitialCard getInitialCard() {
            return initialCard;
        }

        public void setInitialCard(InitialCard initialCard) {
            this.initialCard = initialCard;
        }

        public SleepCoachingDetails withInitialCard(InitialCard initialCard) {
            this.initialCard = initialCard;
            return this;
        }

    }
//-----------------------------------com.example..SleepCoachingLabels.java-----------------------------------

    public class SleepCoachingLabels implements Serializable{

        @SerializedName("header")
        @Expose
        private Header_ header;
        @SerializedName("labels")
        @Expose
        private Labels_ labels;
        @SerializedName("buttons")
        @Expose
        private Buttons_ buttons;

        /**
         * No args constructor for use in serialization
         */
        public SleepCoachingLabels() {
        }

        /**
         * @param labels
         * @param buttons
         * @param header
         */
        public SleepCoachingLabels(Header_ header, Labels_ labels, Buttons_ buttons) {
            super();
            this.header = header;
            this.labels = labels;
            this.buttons = buttons;
        }

        public Header_ getHeader() {
            return header;
        }

        public void setHeader(Header_ header) {
            this.header = header;
        }

        public SleepCoachingLabels withHeader(Header_ header) {
            this.header = header;
            return this;
        }

        public Labels_ getLabels() {
            return labels;
        }

        public void setLabels(Labels_ labels) {
            this.labels = labels;
        }

        public SleepCoachingLabels withLabels(Labels_ labels) {
            this.labels = labels;
            return this;
        }

        public Buttons_ getButtons() {
            return buttons;
        }

        public void setButtons(Buttons_ buttons) {
            this.buttons = buttons;
        }

        public SleepCoachingLabels withButtons(Buttons_ buttons) {
            this.buttons = buttons;
            return this;
        }

    }
//-----------------------------------com.example..SleepGraphLabels.java-----------------------------------


    public class SleepGraphLabels implements Serializable{

        @SerializedName("label")
        @Expose
        private String label;
        @SerializedName("footer_label")
        @Expose
        private String footerLabel;
        @SerializedName("session_label")
        @Expose
        private String sessionLabel;

        /**
         * No args constructor for use in serialization
         */
        public SleepGraphLabels() {
        }

        /**
         * @param sessionLabel
         * @param footerLabel
         * @param label
         */
        public SleepGraphLabels(String label, String footerLabel, String sessionLabel) {
            super();
            this.label = label;
            this.footerLabel = footerLabel;
            this.sessionLabel = sessionLabel;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public SleepGraphLabels withLabel(String label) {
            this.label = label;
            return this;
        }

        public String getFooterLabel() {
            return footerLabel;
        }

        public void setFooterLabel(String footerLabel) {
            this.footerLabel = footerLabel;
        }

        public SleepGraphLabels withFooterLabel(String footerLabel) {
            this.footerLabel = footerLabel;
            return this;
        }

        public String getSessionLabel() {
            return sessionLabel;
        }

        public void setSessionLabel(String sessionLabel) {
            this.sessionLabel = sessionLabel;
        }

        public SleepGraphLabels withSessionLabel(String sessionLabel) {
            this.sessionLabel = sessionLabel;
            return this;
        }

    }

//    -----------------------------------com.example..Today.java-----------------------------------


    public class Today implements Serializable{

        @SerializedName("total_duration")
        @Expose
        private Object totalDuration;
        @SerializedName("x_label")
        @Expose
        private Object xLabel;

        /**
         * No args constructor for use in serialization
         */
        public Today() {
        }

        /**
         * @param totalDuration
         * @param xLabel
         */
        public Today(Object totalDuration, Object xLabel) {
            super();
            this.totalDuration = totalDuration;
            this.xLabel = xLabel;
        }

        public Object getTotalDuration() {
            return totalDuration;
        }

        public void setTotalDuration(Object totalDuration) {
            this.totalDuration = totalDuration;
        }

        public Today withTotalDuration(Object totalDuration) {
            this.totalDuration = totalDuration;
            return this;
        }

        public Object getXLabel() {
            return xLabel;
        }

        public void setXLabel(Object xLabel) {
            this.xLabel = xLabel;
        }

        public Today withXLabel(Object xLabel) {
            this.xLabel = xLabel;
            return this;
        }
    }
}