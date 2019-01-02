package com.example.poplify.baby_guru_sample.pojo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTimerResponse {

@SerializedName("sleep_coaching_details")
@Expose
private SleepCoachingDetails sleepCoachingDetails;
@SerializedName("sleep_coaching_labels")
@Expose
private SleepCoachingLabels sleepCoachingLabels;
@SerializedName("before_you_start")
@Expose
private BeforeYouStart beforeYouStart;
@SerializedName("guru_tips")
@Expose
private GuruTips guruTips;
@SerializedName("crying_scale_data")
@Expose
private CryingScaleData cryingScaleData;

/**
* No args constructor for use in serialization
* 
*/
public GetTimerResponse() {
}

/**
* 
* @param beforeYouStart
* @param sleepCoachingLabels
* @param sleepCoachingDetails
* @param guruTips
* @param cryingScaleData
*/
public GetTimerResponse(SleepCoachingDetails sleepCoachingDetails, SleepCoachingLabels sleepCoachingLabels, BeforeYouStart beforeYouStart, GuruTips guruTips, CryingScaleData cryingScaleData) {
super();
this.sleepCoachingDetails = sleepCoachingDetails;
this.sleepCoachingLabels = sleepCoachingLabels;
this.beforeYouStart = beforeYouStart;
this.guruTips = guruTips;
this.cryingScaleData = cryingScaleData;
}

public SleepCoachingDetails getSleepCoachingDetails() {
return sleepCoachingDetails;
}

public void setSleepCoachingDetails(SleepCoachingDetails sleepCoachingDetails) {
this.sleepCoachingDetails = sleepCoachingDetails;
}

public SleepCoachingLabels getSleepCoachingLabels() {
return sleepCoachingLabels;
}

public void setSleepCoachingLabels(SleepCoachingLabels sleepCoachingLabels) {
this.sleepCoachingLabels = sleepCoachingLabels;
}

public BeforeYouStart getBeforeYouStart() {
return beforeYouStart;
}

public void setBeforeYouStart(BeforeYouStart beforeYouStart) {
this.beforeYouStart = beforeYouStart;
}

public GuruTips getGuruTips() {
return guruTips;
}

public void setGuruTips(GuruTips guruTips) {
this.guruTips = guruTips;
}

public CryingScaleData getCryingScaleData() {
return cryingScaleData;
}

public void setCryingScaleData(CryingScaleData cryingScaleData) {
this.cryingScaleData = cryingScaleData;
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
         *
         */
        public GuruTips() {
        }

        /**
         *
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

        public List<MainContent> getMainContent() {
            return mainContent;
        }

        public void setMainContent(List<MainContent> mainContent) {
            this.mainContent = mainContent;
        }

    }

    public class Head {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("sub_title")
        @Expose
        private String subTitle;

        /**
         * No args constructor for use in serialization
         *
         */
        public Head() {
        }

        /**
         *
         * @param subTitle
         * @param title
         */
        public Head(String title, String subTitle) {
            super();
            this.title = title;
            this.subTitle = subTitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
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
         *
         */
        public Header() {
        }

        /**
         *
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

        public String getSleepCoaching() {
            return sleepCoaching;
        }

        public void setSleepCoaching(String sleepCoaching) {
            this.sleepCoaching = sleepCoaching;
        }

        public String getGuruTips() {
            return guruTips;
        }

        public void setGuruTips(String guruTips) {
            this.guruTips = guruTips;
        }

        public String getCryingScale() {
            return cryingScale;
        }

        public void setCryingScale(String cryingScale) {
            this.cryingScale = cryingScale;
        }

        public String getBeforeYouStart() {
            return beforeYouStart;
        }

        public void setBeforeYouStart(String beforeYouStart) {
            this.beforeYouStart = beforeYouStart;
        }

        public String getImportantInfo() {
            return importantInfo;
        }

        public void setImportantInfo(String importantInfo) {
            this.importantInfo = importantInfo;
        }

    }

    public class InfiniteCard {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("details")
        @Expose
        private String details;

        /**
         * No args constructor for use in serialization
         *
         */
        public InfiniteCard() {
        }

        /**
         *
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

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

    }

    public class InitialCard {

        @SerializedName("initial_card")
        @Expose
        private String initialCard;

        /**
         * No args constructor for use in serialization
         *
         */
        public InitialCard() {
        }

        /**
         *
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
         *
         */
        public Labels() {
        }

        /**
         *
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

        public String getTotalSettlingTimer() {
            return totalSettlingTimer;
        }

        public void setTotalSettlingTimer(String totalSettlingTimer) {
            this.totalSettlingTimer = totalSettlingTimer;
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
         *
         */
        public MainContent() {
        }

        /**
         *
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

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

    public class Method1 {

        @SerializedName("level")
        @Expose
        private String level;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("suggestion")
        @Expose
        private String suggestion;
        @SerializedName("suggestion_title")
        @Expose
        private String suggestionTitle;

        /**
         * No args constructor for use in serialization
         *
         */
        public Method1() {
        }

        /**
         *
         * @param title
         * @param level
         * @param suggestionTitle
         * @param suggestion
         */
        public Method1(String level, String title, String suggestion, String suggestionTitle) {
            super();
            this.level = level;
            this.title = title;
            this.suggestion = suggestion;
            this.suggestionTitle = suggestionTitle;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(String suggestion) {
            this.suggestion = suggestion;
        }

        public String getSuggestionTitle() {
            return suggestionTitle;
        }

        public void setSuggestionTitle(String suggestionTitle) {
            this.suggestionTitle = suggestionTitle;
        }

    }

    public class Method2 {

        @SerializedName("level")
        @Expose
        private String level;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("suggestion")
        @Expose
        private String suggestion;
        @SerializedName("suggestion_title")
        @Expose
        private String suggestionTitle;

        /**
         * No args constructor for use in serialization
         *
         */
        public Method2() {
        }

        /**
         *
         * @param title
         * @param level
         * @param suggestionTitle
         * @param suggestion
         */
        public Method2(String level, String title, String suggestion, String suggestionTitle) {
            super();
            this.level = level;
            this.title = title;
            this.suggestion = suggestion;
            this.suggestionTitle = suggestionTitle;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(String suggestion) {
            this.suggestion = suggestion;
        }

        public String getSuggestionTitle() {
            return suggestionTitle;
        }

        public void setSuggestionTitle(String suggestionTitle) {
            this.suggestionTitle = suggestionTitle;
        }

    }

    public class SleepCoachingDetails {

        @SerializedName("choose_methods")
        @Expose
        private List<ChooseMethod> chooseMethods = null;
        @SerializedName("initial_card")
        @Expose
        private InitialCard initialCard;

        /**
         * No args constructor for use in serialization
         *
         */
        public SleepCoachingDetails() {
        }

        /**
         *
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

        public InitialCard getInitialCard() {
            return initialCard;
        }

        public void setInitialCard(InitialCard initialCard) {
            this.initialCard = initialCard;
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
         *
         */
        public SleepCoachingLabels() {
        }

        /**
         *
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

        public Labels getLabels() {
            return labels;
        }

        public void setLabels(Labels labels) {
            this.labels = labels;
        }

        public Buttons getButtons() {
            return buttons;
        }

        public void setButtons(Buttons buttons) {
            this.buttons = buttons;
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
         *
         */
        public Footer() {
        }

        /**
         *
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

        public String getButton() {
            return button;
        }

        public void setButton(String button) {
            this.button = button;
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
         *
         */
        public Detail() {
        }

        /**
         *
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

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswers() {
            return answers;
        }

        public void setAnswers(String answers) {
            this.answers = answers;
        }

    }

    public class CryingScaleData {

        @SerializedName("head")
        @Expose
        private Head head;
        @SerializedName("crying_scale")
        @Expose
        private CryingScale cryingScale;

        /**
         * No args constructor for use in serialization
         *
         */
        public CryingScaleData() {
        }

        /**
         *
         * @param cryingScale
         * @param head
         */
        public CryingScaleData(Head head, CryingScale cryingScale) {
            super();
            this.head = head;
            this.cryingScale = cryingScale;
        }

        public Head getHead() {
            return head;
        }

        public void setHead(Head head) {
            this.head = head;
        }

        public CryingScale getCryingScale() {
            return cryingScale;
        }

        public void setCryingScale(CryingScale cryingScale) {
            this.cryingScale = cryingScale;
        }

    }

    public class CryingScale {

        @SerializedName("method_1")
        @Expose
        private List<Method1> method1 = null;
        @SerializedName("method_2")
        @Expose
        private List<Method2> method2 = null;
        @SerializedName("after_level_5")
        @Expose
        private String afterLevel5;

        /**
         * No args constructor for use in serialization
         *
         */
        public CryingScale() {
        }

        /**
         *
         * @param afterLevel5
         * @param method2
         * @param method1
         */
        public CryingScale(List<Method1> method1, List<Method2> method2, String afterLevel5) {
            super();
            this.method1 = method1;
            this.method2 = method2;
            this.afterLevel5 = afterLevel5;
        }

        public List<Method1> getMethod1() {
            return method1;
        }

        public void setMethod1(List<Method1> method1) {
            this.method1 = method1;
        }

        public List<Method2> getMethod2() {
            return method2;
        }

        public void setMethod2(List<Method2> method2) {
            this.method2 = method2;
        }

        public String getAfterLevel5() {
            return afterLevel5;
        }

        public void setAfterLevel5(String afterLevel5) {
            this.afterLevel5 = afterLevel5;
        }

    }

    public class ChooseMethod {

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
         *
         */
        public ChooseMethod() {
        }

        /**
         *
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getListenToSam() {
            return listenToSam;
        }

        public void setListenToSam(String listenToSam) {
            this.listenToSam = listenToSam;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getReadMore() {
            return readMore;
        }

        public void setReadMore(String readMore) {
            this.readMore = readMore;
        }

        public String getDefaultName() {
            return defaultName;
        }

        public void setDefaultName(String defaultName) {
            this.defaultName = defaultName;
        }

        public List<String> getFullDescription() {
            return fullDescription;
        }

        public void setFullDescription(List<String> fullDescription) {
            this.fullDescription = fullDescription;
        }

        public List<Card> getCards() {
            return cards;
        }

        public void setCards(List<Card> cards) {
            this.cards = cards;
        }

        public List<InfiniteCard> getInfiniteCards() {
            return infiniteCards;
        }

        public void setInfiniteCards(List<InfiniteCard> infiniteCards) {
            this.infiniteCards = infiniteCards;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

    }
    public class Card {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("details")
        @Expose
        private String details;

        /**
         * No args constructor for use in serialization
         *
         */
        public Card() {
        }

        /**
         *
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

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
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
         *
         */
        public Buttons() {
        }

        /**
         *
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

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getBabyIsAsleep() {
            return babyIsAsleep;
        }

        public void setBabyIsAsleep(String babyIsAsleep) {
            this.babyIsAsleep = babyIsAsleep;
        }

        public String getCryingScale() {
            return cryingScale;
        }

        public void setCryingScale(String cryingScale) {
            this.cryingScale = cryingScale;
        }

        public String getDone() {
            return done;
        }

        public void setDone(String done) {
            this.done = done;
        }

        public String getResumeCoaching() {
            return resumeCoaching;
        }

        public void setResumeCoaching(String resumeCoaching) {
            this.resumeCoaching = resumeCoaching;
        }

        public String getStopCoaching() {
            return stopCoaching;
        }

        public void setStopCoaching(String stopCoaching) {
            this.stopCoaching = stopCoaching;
        }

        public String getConsistent() {
            return consistent;
        }

        public void setConsistent(String consistent) {
            this.consistent = consistent;
        }

        public String getInconsistent() {
            return inconsistent;
        }

        public void setInconsistent(String inconsistent) {
            this.inconsistent = inconsistent;
        }

        public String getOk() {
            return ok;
        }

        public void setOk(String ok) {
            this.ok = ok;
        }

        public String getNoProblem() {
            return noProblem;
        }

        public void setNoProblem(String noProblem) {
            this.noProblem = noProblem;
        }

        public String getContactUs() {
            return contactUs;
        }

        public void setContactUs(String contactUs) {
            this.contactUs = contactUs;
        }

        public String getSkip() {
            return skip;
        }

        public void setSkip(String skip) {
            this.skip = skip;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

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
         *
         */
        public BeforeYouStart() {
        }

        /**
         *
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Detail> getDetails() {
            return details;
        }

        public void setDetails(List<Detail> details) {
            this.details = details;
        }

        public Footer getFooter() {
            return footer;
        }

        public void setFooter(Footer footer) {
            this.footer = footer;
        }

    }
}