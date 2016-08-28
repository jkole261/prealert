package prealert30;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
public class TwitterAlert {
private static	Twitter live;

	public TwitterAlert(){
		//live = new TwitterFactory().getInstance("EfdpreIt", "EFD2014itc");
		//live = new TwitterFactory().getInstance();
		//live.setOAuthConsumer("YSBK2owvKdKZUIcFYm91X6sCA", "BGbNhy6OY1W3AttbY1zTEgZhJNeeeiuWjxAwpvSPwLfbqFFIAw");
		//AccessToken accessToken = new AccessToken("4846278993-WpFqNMYwibTWcUQFC0AM4hA9IYi7atfxX51oywo", "3wnxOqORQSLA0MySiQWpR4Shx8VzfNyI0M5QDFzt15J7m");
		//live.setOAuthAccessToken(accessToken);
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("YSBK2owvKdKZUIcFYm91X6sCA")
		  .setOAuthConsumerSecret("BGbNhy6OY1W3AttbY1zTEgZhJNeeeiuWjxAwpvSPwLfbqFFIAw")
		  .setOAuthAccessToken("4846278993-WpFqNMYwibTWcUQFC0AM4hA9IYi7atfxX51oywo")
		  .setOAuthAccessTokenSecret("	3wnxOqORQSLA0MySiQWpR4Shx8VzfNyI0M5QDFzt15J7m");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter live = tf.getInstance();
		
	}
	public static void update (String message){
		try  //Checks to see if it can send message
		{
			//TwitterAlert update = null;
			System.out.println("here1");
			internal(message);//this
		}
		catch( TwitterException e)
		{
			
			System.out.println("Trying again....");
			
			try{
				//TwitterAlert update = null;
				System.out.println("here2");
				internal(message);//this
			}
			catch( TwitterException e1){
				System.out.println("Unable to send twitter message.. really");
			}
			
		}
	}
	
	private static void internal(String message)throws TwitterException{
        if (message == null) {
        	System.out.println("here3");
            System.out.println("no message included");
        }
        System.out.println("here4");
		Status status = live.updateStatus("dd");
        
        System.out.println("Successfully updated twitter to [" + status.getText() + "].");
    }
}
