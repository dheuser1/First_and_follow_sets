import java.util.*;


public class driver 
{
	public static void main(String[] args) 
	{
		//add in symbols and production rules 
		grammar g = new grammar();
		
		g.alphabet.add(g.new symbol("START", false));
		g.alphabet.get(g.alphabet.size()-1).rules.add("PROG");
		
		g.alphabet.add(g.new symbol("PROG", false));
		g.alphabet.get(g.alphabet.size()-1).rules.add("BLOCK #");
		
		g.alphabet.add(g.new symbol("BLOCK", false));
		g.alphabet.get(g.alphabet.size()-1).rules.add("begin BODY end");
		
		g.alphabet.add(g.new symbol("BODY", false));
		g.alphabet.get(g.alphabet.size()-1).rules.add("BODY ; S");
		g.alphabet.get(g.alphabet.size()-1).rules.add("S");
		
		g.alphabet.add(g.new symbol("S", false));
		g.alphabet.get(g.alphabet.size()-1).rules.add("if E then S else S fi");
		g.alphabet.get(g.alphabet.size()-1).rules.add("if E then S fi");
		g.alphabet.get(g.alphabet.size()-1).rules.add("i = E");
		g.alphabet.get(g.alphabet.size()-1).rules.add("BLOCK");
		
		g.alphabet.add(g.new symbol("E", false));
		g.alphabet.get(g.alphabet.size()-1).rules.add("E + T");
		g.alphabet.get(g.alphabet.size()-1).rules.add("E - T");
		g.alphabet.get(g.alphabet.size()-1).rules.add("T");
		
		g.alphabet.add(g.new symbol("T", false));
		g.alphabet.get(g.alphabet.size()-1).rules.add("T * F");
		g.alphabet.get(g.alphabet.size()-1).rules.add("T / F");
		g.alphabet.get(g.alphabet.size()-1).rules.add("F");
		
		g.alphabet.add(g.new symbol("F", false));
		g.alphabet.get(g.alphabet.size()-1).rules.add("( E )");
		g.alphabet.get(g.alphabet.size()-1).rules.add("const");
		
		g.alphabet.add(g.new symbol("#", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("#");
		
		g.alphabet.add(g.new symbol("begin", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("begin");
		
		g.alphabet.add(g.new symbol("end", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("end");
		
		g.alphabet.add(g.new symbol(";", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add(";");
		
		g.alphabet.add(g.new symbol("if", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("if");
		
		g.alphabet.add(g.new symbol("then", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("then");
		
		g.alphabet.add(g.new symbol("else", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("else");
		
		g.alphabet.add(g.new symbol("fi", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("fi");
		
		g.alphabet.add(g.new symbol("i", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("i");
		
		g.alphabet.add(g.new symbol("=", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("=");
		
		g.alphabet.add(g.new symbol("+", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("+");
		
		g.alphabet.add(g.new symbol("-", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("-");
		
		g.alphabet.add(g.new symbol("*", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("*");
		
		g.alphabet.add(g.new symbol("/", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("/");
		
		g.alphabet.add(g.new symbol("(", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("(");
		
		g.alphabet.add(g.new symbol(")", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add(")");
		
		g.alphabet.add(g.new symbol("const", true));
		g.alphabet.get(g.alphabet.size()-1).rules.add("const");
		
		System.out.println("1st grammar");
		g.find_first();
		g.find_follow();
		g.print();
		
		grammar g2 = new grammar();
		
		g2.alphabet.add(g2.new symbol("START", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("PROG");
		
		g2.alphabet.add(g2.new symbol("PROG", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("BLOCK #");
		
		g2.alphabet.add(g2.new symbol("BLOCK", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("begin BODY end");
		
		g2.alphabet.add(g2.new symbol("BODY", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("S BODY'");
		
		g2.alphabet.add(g2.new symbol("BODY'", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("; S BODY'");
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("lambda");
		
		g2.alphabet.add(g2.new symbol("S", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("BLOCK");
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("IFST");
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("ASSI");
		
		g2.alphabet.add(g2.new symbol("IFST", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("if E then S IF'");
		
		g2.alphabet.add(g2.new symbol("IF'", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("else S fi");
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("fi");
		
		g2.alphabet.add(g2.new symbol("ASSI", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("V = E");
		
		g2.alphabet.add(g2.new symbol("V", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("id");
		
		g2.alphabet.add(g2.new symbol("E", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("T E'");
		
		g2.alphabet.add(g2.new symbol("E'", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("+ T E'");
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("- T E'");
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("lambda");
		
		g2.alphabet.add(g2.new symbol("T", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("F T'");
		
		g2.alphabet.add(g2.new symbol("T'", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("* F T'");
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("/ F T'");
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("lambda");
		
		g2.alphabet.add(g2.new symbol("F", false));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("V");
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("( E )");
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("const");
		
		g2.alphabet.add(g2.new symbol("#", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("#");
		
		g2.alphabet.add(g2.new symbol("begin", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("begin");
		
		g2.alphabet.add(g2.new symbol("end", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("end");
		
		g2.alphabet.add(g2.new symbol(";", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add(";");
		
		g2.alphabet.add(g2.new symbol("lambda", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("lambda");
		
		g2.alphabet.add(g2.new symbol("if", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("if");
		
		g2.alphabet.add(g2.new symbol("then", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("then");
		
		g2.alphabet.add(g2.new symbol("else", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("else");
		
		g2.alphabet.add(g2.new symbol("fi", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("fi");
		
		g2.alphabet.add(g2.new symbol("=", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("=");
		
		g2.alphabet.add(g2.new symbol("id", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("id");
		
		g2.alphabet.add(g2.new symbol("+", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("+");
		
		g2.alphabet.add(g2.new symbol("-", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("-");
		
		g2.alphabet.add(g2.new symbol("*", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("*");
		
		g2.alphabet.add(g2.new symbol("/", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("/");
		
		g2.alphabet.add(g2.new symbol("(", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("(");
		
		g2.alphabet.add(g2.new symbol(")", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add(")");
		
		g2.alphabet.add(g2.new symbol("const", true));
		g2.alphabet.get(g2.alphabet.size()-1).rules.add("const");
		
		System.out.println("2nd grammar");
		g2.find_first();
		g2.find_follow();
		g2.print();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		Set<String> test = new HashSet<String>();
		Set<String> test2 = new HashSet<String>();
		
		
		
		
		test2.add("3");
		test2.add("2");
		System.out.println(test2.addAll(test2));
		
		System.out.println(test2.toString());
		System.out.println(test2.remove("2"));
		System.out.println(test2.remove("2"));
		System.out.println(test2.toString());
		
		test.add("1");
		test.add("2");
		System.out.println(test.add("1"));
		System.out.println(test.toString());
		System.out.println(test.addAll(test2));
		System.out.println(test.toString());
		*/
		
	}
}
