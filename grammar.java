import java.util.*;


public class grammar 
{
	public ArrayList<symbol> alphabet = new ArrayList<symbol>();
	
	public void print()
	{
		for(int i=0; i<alphabet.size(); i++)
		{
			alphabet.get(i).print();
		}
	}
	
	public int find(String item)
	{
		for(int i=0; i<alphabet.size(); i++)
		{
			if(alphabet.get(i).name.equals(item))
			{
				return i;
			}
		}
		return -1;
	}
	
	public void find_first()
	{
		boolean is_done=false;
		boolean changed=false;
		//keep running until it does not change
		while(is_done==false)
		{
			is_done=true;
			for(int i=0; i<alphabet.size(); i++)
			{
				changed=alphabet.get(i).find_first();
				//part of it changed so keep going
				if(changed==true)
				{
					is_done=false;
				}
			}
		}
	}
	
	public void find_follow() 
	{
		boolean is_done=false;
		boolean changed=false;
		//keep running until it does not change 
		while(is_done==false)
		{
			is_done=true;
			for(int i=0; i<alphabet.size(); i++)
			{
				changed=alphabet.get(i).find_follow();
				//part of it changed so keep going
				if(changed==true)
				{
					is_done=false;
				}
			}
		}
	}
	
	public class symbol 
	{
		private String name;
		//production rules of this symbol
		public ArrayList<String> rules = new ArrayList<String>();
		private Set<String> first = new HashSet<String>();
		private Set<String> follow = new HashSet<String>();
		//true if this is a terminal symbol 
		private boolean is_terminal;
		//true if this set can make lambda will be set to false by default
		private boolean lambda_generate;
		
		public symbol(String n, boolean it)
		{
			name=n;
			is_terminal=it;
			lambda_generate=false;
		}
		
		public boolean find_follow()
		{
			boolean changed=false;
			ArrayList<String> rule_elements=new ArrayList<String>();
			
			for(int i=0; i<rules.size(); i++)
			{
				//put $ into start
				if(alphabet.get(find("START")).follow.add("$")==true)
				{
					changed=true;
				}
				
				rule_elements.clear();
				StringTokenizer st = new StringTokenizer(rules.get(i), " ", false);
				while(st.hasMoreTokens())
				{
					rule_elements.add(st.nextToken());
				}
				//loop rule_elements
				for(int j=0; j<rule_elements.size(); j++)
				{
					int left_index=find(rule_elements.get(j));
					if(left_index==-1)
					{
						System.out.println("error follow 1 with"+rule_elements.get(j));
						System.exit(1);
					}
					
					//looking at last element in rule so have form A->&B
					if(j==rule_elements.size()-1)
					{
						//only nonterminals get follow sets
						if(alphabet.get(left_index).is_terminal==false &&alphabet.get(left_index).follow.addAll(follow)==true)
						{
							changed=true;
						}
						break;
					}
					//offset by one so it is not always being added to itself
					for(int k=j+1; k<rule_elements.size(); k++)
					{
						int right_index=find(rule_elements.get(k));
						if(right_index==-1)
						{
							System.out.println("error follow 2 with"+rule_elements.get(k));
							System.exit(1);
						}
						Set<String> temp = new HashSet<String>();
							
						//A->&B@ found end of first @
						if(alphabet.get(right_index).lambda_generate==false)
						{
							temp.addAll(alphabet.get(right_index).first);
							temp.remove("lambda");
							//only nonterminals get follow sets
							if(alphabet.get(left_index).is_terminal==false && alphabet.get(left_index).follow.addAll(temp)==true)
							{
								changed=true;
							}
							//found end of first set of @ so end loop
							temp.clear();
							break;
						}
						//generates lambda and is not the end of @
						else if(alphabet.get(right_index).lambda_generate==true && k!=rule_elements.size()-1)
						{
							temp.addAll(alphabet.get(right_index).first);
						}
						//must be the end and they all make lambda
						else
						{
							//only nonterminals get follow sets
							if(alphabet.get(left_index).is_terminal==false && alphabet.get(left_index).follow.addAll(follow)==true)
							{
								changed=true;
							}
							temp.clear();
						}
					}
				}
			}
			return changed;
		}
		
		public boolean find_first()
		{
			boolean changed=false; 
			for(int i=0; i<rules.size(); i++)
			{
				StringTokenizer st = new StringTokenizer(rules.get(i), " ", false);
				while(st.hasMoreTokens())
				{
					String working_symbol=st.nextToken();
					int look_up = find(working_symbol);
					
					//this should never be hit
					if(look_up==-1)
					{
						System.out.println(working_symbol+" error_1");
						System.exit(1);
					}
					//found terminal symbol try to add it and then stop for this rule
					if(alphabet.get(look_up).is_terminal==true)
					{
						if(first.add(working_symbol)==true)
						{
							changed=true;
							//if nonterminal symbol is lambda then this generates lambda
							if(working_symbol.equals("lambda"))
							{
								lambda_generate=true;
							}
						}
						//found nonterminal so stop looking
						break;
					}
					//do not want nonterminal symbol to look at itself
					else if(!name.equals(alphabet.get(look_up).name))
					{
						//found nonterminal symbol that does not make lambda 
						if(alphabet.get(look_up).lambda_generate==false)
						{
							if(first.addAll(alphabet.get(look_up).first)==true)
							{
								changed=true;
							}
							//found nonterminal that does not make lambda so stop looking
							break;
						}
						//found terminal symbol that makes lambda but it is not the last one so do not add lambda 
						else if(st.countTokens()!=0)
						{
							alphabet.get(look_up).first.remove("lambda");
							if(first.addAll(alphabet.get(look_up).first)==true)
							{
								changed=true;
							}
							alphabet.get(look_up).first.add("lambda");
						}
						//must be the last symbol
						else if(first.addAll(alphabet.get(look_up).first)==true)
						{
							changed=true;
							//if the last symbol generates lambda then so does this
							if(alphabet.get(look_up).lambda_generate==true)
							{
								lambda_generate=true;
							}
						}
					}
				}
			}
			return changed;
		}
		
		public void print()
		{
			System.out.println(name);
			System.out.println("first set is "+first.toString());
			if(is_terminal==false)
			{
				System.out.println("follow set is "+follow.toString());
			}
			System.out.println("****************************************************");
		}
	}
}
