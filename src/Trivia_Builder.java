import org.json.JSONArray;
import org.json.JSONObject;

public class Trivia_Builder {
	
	private String[] questions ;
	private String category;
	private String type ;
	private String question;
	private String correct_answer;
	private JSONArray incorrect_answers;
	
	public Trivia_Builder(String category, String type,	 String question, String correct_answer,JSONArray incorrect_answers)
	{
		this.category = category;
		this.type = type;
		this.question = question;
		this.correct_answer = correct_answer;
		this.incorrect_answers = incorrect_answers;
	}
	
	public String [] splitTheIncorrectAnsers() 
	{
		String s[] = new String[3];
		for(int i = 0; i < this.incorrect_answers.length(); i++)
		{
			if(!(this.type.equals("boolean")))
			{
				s[i] = this.incorrect_answers.getString(i);
			}				
		}
		return s;
	}
	
	public String DisplayQuestion() 
	{
		return("category: "+this.category+"\n"+"Type: "+this.type+"\n"+this.question+"\n"+this.correct_answer);
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getCorrect_answer() {
		return correct_answer;
	}
	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}
	public JSONArray getIncorrect_answers() {
		return incorrect_answers;
	}
	public void setIncorrect_answers(JSONArray incorrect_answers) {
		this.incorrect_answers = incorrect_answers;
	}
	
	

}
