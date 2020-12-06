/*
requirements:

    Any non-member (guest) can search and view questions. However, to add or upvote a question, they have to become a member.
    Members should be able to post new questions.
    Members should be able to add an answer to an open question.
    Members can add comments to any question or answer.
    A member can upvote a question, answer or comment.
    Members can flag a question, answer or comment, for serious problems or moderator attention.
    Any member can add a bounty to their question to draw attention.
    Members will earn badges for being helpful.
    Members can vote to close a question; Moderators can close or reopen any question.
    Members can add tags to their questions. A tag is a word or phrase that describes the topic of the question.
    Members can vote to delete extremely off-topic or very low-quality questions.
    Moderators can close a question or undelete an already deleted question.
    The system should also be able to identify most frequently used tags in the questions.

*/
class User {

	int guestId;
	Search searchObj;

	public List<Question> getQuestions(String searchString);

}

class Member extends User{

	Account account;
	List<Badge> badges;

	public void addQuestion(Question question);
	public void addComment(Entity entity, Comment comment);
	public void addAnswer(Question question, Answer answer);
	public void vote(Entity entity, VoteType voteType);
	public void addTag(Question question, Tag tag);
	public void flag(Entity entity);
	public List<Badge> getBadges();

}

class Account {

	String name;
	Address address;
	int accountId;

	String userName;
	String password;
	String email;
	
	AccountStatus accountStatus;

	int reputation;
}

class Moderator extends Member {

	public Boolean closeQuestion(Question question);
	public Boolean restoreQuestion(Quetion question);

}

class Admin extends Member {

	public Boolean blockMember(Member member);
	public Boolean unblockMember(Member member);

}

public enum AccountStatus {

	BLOCKED, ACTIVE, CLOSED;
}

public enum VoteType {

	UPVOTE, DOWNVOTE, CLOSEVOTE, DELETEVOTE;
}

public class Badge {

	String name;
	String description;	
}

public class Entity {

	int entityId;
	Member creator;
	Map<VoteType, Integer> votes;
	Date createdDate;
	List<Comment> comments;

	public boolean flagEntity(Member member);
	public boolean voteEntity(VoteType voteType);
	public boolean addComment(Comment comment);

}

public class Comment extends Entity {

	String message;
}

public class Question extends Entity {

	List<EditHistory> editHistoryList;
	List<Answer> answerList;
	List<Tag> tags;
	String title;
	String description;
	QuestionStatus status;

	public boolean addQuestion();
	public boolean addTag(Tag tag);
}

public class Answer extends Entity {

	String answer;
	Boolean isAccepted;
	public boolean addAnswer(Question question);

}

public enum QuestionStatus {

	ACTIVE, BOUNTIED, CLOSED, FLAGGED;
}

public class Tag {

	String name;
	String description;

}
public class EditHistory {

	int editHistoryId;
	Member creator;
	Date creationDate;
	Question prevQuestion;
	Question updatedQuestion;
}


// ref: https://www.youtube.com/watch?v=eTB0nxb-j-Q
