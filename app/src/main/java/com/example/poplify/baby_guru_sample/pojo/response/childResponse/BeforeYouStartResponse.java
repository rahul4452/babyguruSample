package com.example.poplify.baby_guru_sample.pojo.response.childResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class BeforeYouStartResponse {

    @SerializedName("has_child")
    @Expose
    private Boolean hasChild;
    @SerializedName("has_sleep_coaching")
    @Expose
    private Boolean hasSleepCoaching;
    @SerializedName("before_you_start")
    @Expose
    private BeforeYouStart beforeYouStart;
    @SerializedName("guru_tips")
    @Expose
    private GuruTips guruTips;
    @SerializedName("sleep_coaching_labels")
    @Expose
    private SleepCoachingLabels sleepCoachingLabels;
    @SerializedName("sub_parent_login")
    @Expose
    private Boolean subParentLogin;

    /**
     * No args constructor for use in serialization
     */
    public BeforeYouStartResponse() {
    }

    /**
     * @param sleepCoachingLabels
     * @param beforeYouStart
     * @param hasSleepCoaching
     * @param guruTips
     * @param subParentLogin
     * @param hasChild
     */
    public BeforeYouStartResponse(Boolean hasChild, Boolean hasSleepCoaching, BeforeYouStart beforeYouStart, GuruTips guruTips, SleepCoachingLabels sleepCoachingLabels, Boolean subParentLogin) {
        super();
        this.hasChild = hasChild;
        this.hasSleepCoaching = hasSleepCoaching;
        this.beforeYouStart = beforeYouStart;
        this.guruTips = guruTips;
        this.sleepCoachingLabels = sleepCoachingLabels;
        this.subParentLogin = subParentLogin;
    }

    public Boolean getHasChild() {
        return hasChild;
    }

    public void setHasChild(Boolean hasChild) {
        this.hasChild = hasChild;
    }

    public BeforeYouStartResponse withHasChild(Boolean hasChild) {
        this.hasChild = hasChild;
        return this;
    }

    public Boolean getHasSleepCoaching() {
        return hasSleepCoaching;
    }

    public void setHasSleepCoaching(Boolean hasSleepCoaching) {
        this.hasSleepCoaching = hasSleepCoaching;
    }

    public BeforeYouStartResponse withHasSleepCoaching(Boolean hasSleepCoaching) {
        this.hasSleepCoaching = hasSleepCoaching;
        return this;
    }

    public BeforeYouStart getBeforeYouStart() {
        return beforeYouStart;
    }

    public void setBeforeYouStart(BeforeYouStart beforeYouStart) {
        this.beforeYouStart = beforeYouStart;
    }

    public BeforeYouStartResponse withBeforeYouStart(BeforeYouStart beforeYouStart) {
        this.beforeYouStart = beforeYouStart;
        return this;
    }

    public GuruTips getGuruTips() {
        return guruTips;
    }

    public void setGuruTips(GuruTips guruTips) {
        this.guruTips = guruTips;
    }

    public BeforeYouStartResponse withGuruTips(GuruTips guruTips) {
        this.guruTips = guruTips;
        return this;
    }

    public SleepCoachingLabels getSleepCoachingLabels() {
        return sleepCoachingLabels;
    }

    public void setSleepCoachingLabels(SleepCoachingLabels sleepCoachingLabels) {
        this.sleepCoachingLabels = sleepCoachingLabels;
    }

    public BeforeYouStartResponse withSleepCoachingLabels(SleepCoachingLabels sleepCoachingLabels) {
        this.sleepCoachingLabels = sleepCoachingLabels;
        return this;
    }

    public Boolean getSubParentLogin() {
        return subParentLogin;
    }

    public void setSubParentLogin(Boolean subParentLogin) {
        this.subParentLogin = subParentLogin;
    }

    public BeforeYouStartResponse withSubParentLogin(Boolean subParentLogin) {
        this.subParentLogin = subParentLogin;
        return this;
    }

    public class BeforeYouStart {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("details")
        @Expose
        private List<Detail> details = null;
        @SerializedName("footer")
        @Expose
        private Footer footer;

        /**
         * No args constructor for use in serialization
         */
        public BeforeYouStart() {
        }

        /**
         * @param footer
         * @param title
         * @param details
         * @param description
         */
        public BeforeYouStart(String title, String description, List<Detail> details, Footer footer) {
            super();
            this.title = title;
            this.description = description;
            this.details = details;
            this.footer = footer;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public BeforeYouStart withTitle(String title) {
            this.title = title;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public BeforeYouStart withDescription(String description) {
            this.description = description;
            return this;
        }

        public List<Detail> getDetails() {
            return details;
        }

        public void setDetails(List<Detail> details) {
            this.details = details;
        }

        public BeforeYouStart withDetails(List<Detail> details) {
            this.details = details;
            return this;
        }

        public Footer getFooter() {
            return footer;
        }

        public void setFooter(Footer footer) {
            this.footer = footer;
        }

        public BeforeYouStart withFooter(Footer footer) {
            this.footer = footer;
            return this;
        }

    }


    public class Buttons {

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
        public Buttons() {
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
        public Buttons(String proceedToGo, String start, String babyIsAsleep, String cryingScale, String done, String resumeCoaching, String stopCoaching, String consistent, String inconsistent, String ok, String noProblem, String contactUs, String skip, String feedback, String next) {
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

        public Buttons withProceedToGo(String proceedToGo) {
            this.proceedToGo = proceedToGo;
            return this;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public Buttons withStart(String start) {
            this.start = start;
            return this;
        }

        public String getBabyIsAsleep() {
            return babyIsAsleep;
        }

        public void setBabyIsAsleep(String babyIsAsleep) {
            this.babyIsAsleep = babyIsAsleep;
        }

        public Buttons withBabyIsAsleep(String babyIsAsleep) {
            this.babyIsAsleep = babyIsAsleep;
            return this;
        }

        public String getCryingScale() {
            return cryingScale;
        }

        public void setCryingScale(String cryingScale) {
            this.cryingScale = cryingScale;
        }

        public Buttons withCryingScale(String cryingScale) {
            this.cryingScale = cryingScale;
            return this;
        }

        public String getDone() {
            return done;
        }

        public void setDone(String done) {
            this.done = done;
        }

        public Buttons withDone(String done) {
            this.done = done;
            return this;
        }

        public String getResumeCoaching() {
            return resumeCoaching;
        }

        public void setResumeCoaching(String resumeCoaching) {
            this.resumeCoaching = resumeCoaching;
        }

        public Buttons withResumeCoaching(String resumeCoaching) {
            this.resumeCoaching = resumeCoaching;
            return this;
        }

        public String getStopCoaching() {
            return stopCoaching;
        }

        public void setStopCoaching(String stopCoaching) {
            this.stopCoaching = stopCoaching;
        }

        public Buttons withStopCoaching(String stopCoaching) {
            this.stopCoaching = stopCoaching;
            return this;
        }

        public String getConsistent() {
            return consistent;
        }

        public void setConsistent(String consistent) {
            this.consistent = consistent;
        }

        public Buttons withConsistent(String consistent) {
            this.consistent = consistent;
            return this;
        }

        public String getInconsistent() {
            return inconsistent;
        }

        public void setInconsistent(String inconsistent) {
            this.inconsistent = inconsistent;
        }

        public Buttons withInconsistent(String inconsistent) {
            this.inconsistent = inconsistent;
            return this;
        }

        public String getOk() {
            return ok;
        }

        public void setOk(String ok) {
            this.ok = ok;
        }

        public Buttons withOk(String ok) {
            this.ok = ok;
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

        public String getSkip() {
            return skip;
        }

        public void setSkip(String skip) {
            this.skip = skip;
        }

        public Buttons withSkip(String skip) {
            this.skip = skip;
            return this;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public Buttons withFeedback(String feedback) {
            this.feedback = feedback;
            return this;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public Buttons withNext(String next) {
            this.next = next;
            return this;
        }

    }

    public class Detail {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("answers")
        @Expose
        private String answers;

        /**
         * No args constructor for use in serialization
         */
        public Detail() {
        }

        /**
         * @param id
         * @param answers
         * @param question
         */
        public Detail(Integer id, String question, String answers) {
            super();
            this.id = id;
            this.question = question;
            this.answers = answers;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Detail withId(Integer id) {
            this.id = id;
            return this;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public Detail withQuestion(String question) {
            this.question = question;
            return this;
        }

        public String getAnswers() {
            return answers;
        }

        public void setAnswers(String answers) {
            this.answers = answers;
        }

        public Detail withAnswers(String answers) {
            this.answers = answers;
            return this;
        }

    }

    public class Footer {

        @SerializedName("label")
        @Expose
        private String label;
        @SerializedName("button")
        @Expose
        private String button;

        /**
         * No args constructor for use in serialization
         */
        public Footer() {
        }

        /**
         * @param button
         * @param label
         */
        public Footer(String label, String button) {
            super();
            this.label = label;
            this.button = button;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Footer withLabel(String label) {
            this.label = label;
            return this;
        }

        public String getButton() {
            return button;
        }

        public void setButton(String button) {
            this.button = button;
        }

        public Footer withButton(String button) {
            this.button = button;
            return this;
        }

    }

    public class GuruTips {

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

    public class Header {

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
        public Header() {
        }

        /**
         * @param beforeYouStart
         * @param guruTips
         * @param sleepCoaching
         * @param cryingScale
         * @param chooseMethod
         * @param importantInfo
         */
        public Header(String chooseMethod, String sleepCoaching, String guruTips, String cryingScale, String beforeYouStart, String importantInfo) {
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

        public Header withChooseMethod(String chooseMethod) {
            this.chooseMethod = chooseMethod;
            return this;
        }

        public String getSleepCoaching() {
            return sleepCoaching;
        }

        public void setSleepCoaching(String sleepCoaching) {
            this.sleepCoaching = sleepCoaching;
        }

        public Header withSleepCoaching(String sleepCoaching) {
            this.sleepCoaching = sleepCoaching;
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

        public String getBeforeYouStart() {
            return beforeYouStart;
        }

        public void setBeforeYouStart(String beforeYouStart) {
            this.beforeYouStart = beforeYouStart;
        }

        public Header withBeforeYouStart(String beforeYouStart) {
            this.beforeYouStart = beforeYouStart;
            return this;
        }

        public String getImportantInfo() {
            return importantInfo;
        }

        public void setImportantInfo(String importantInfo) {
            this.importantInfo = importantInfo;
        }

        public Header withImportantInfo(String importantInfo) {
            this.importantInfo = importantInfo;
            return this;
        }

    }

    public class Labels {

        @SerializedName("self_settling_timer")
        @Expose
        private String selfSettlingTimer;
        @SerializedName("total_settling_timer")
        @Expose
        private String totalSettlingTimer;

        /**
         * No args constructor for use in serialization
         */
        public Labels() {
        }

        /**
         * @param selfSettlingTimer
         * @param totalSettlingTimer
         */
        public Labels(String selfSettlingTimer, String totalSettlingTimer) {
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

        public Labels withSelfSettlingTimer(String selfSettlingTimer) {
            this.selfSettlingTimer = selfSettlingTimer;
            return this;
        }

        public String getTotalSettlingTimer() {
            return totalSettlingTimer;
        }

        public void setTotalSettlingTimer(String totalSettlingTimer) {
            this.totalSettlingTimer = totalSettlingTimer;
        }

        public Labels withTotalSettlingTimer(String totalSettlingTimer) {
            this.totalSettlingTimer = totalSettlingTimer;
            return this;
        }

    }

    public class MainContent {

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

    public class SleepCoachingLabels {

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
        public SleepCoachingLabels() {
        }

        /**
         * @param labels
         * @param buttons
         * @param header
         */
        public SleepCoachingLabels(Header header, Labels labels, Buttons buttons) {
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

        public SleepCoachingLabels withHeader(Header header) {
            this.header = header;
            return this;
        }

        public Labels getLabels() {
            return labels;
        }

        public void setLabels(Labels labels) {
            this.labels = labels;
        }

        public SleepCoachingLabels withLabels(Labels labels) {
            this.labels = labels;
            return this;
        }

        public Buttons getButtons() {
            return buttons;
        }

        public void setButtons(Buttons buttons) {
            this.buttons = buttons;
        }

        public SleepCoachingLabels withButtons(Buttons buttons) {
            this.buttons = buttons;
            return this;
        }

    }
}