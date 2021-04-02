import twitter4j.*;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class twitterbot{

        static boolean debug=false;
        final private static int pictureNum=72;
        private static int i=14;

        private static void sendTweet(String text)throws TwitterException{
            Twitter twitter = TwitterFactory.getSingleton();

            StatusUpdate set = new StatusUpdate(text);
            File photo = new File(generateURL());
            set.setMedia(photo);
            twitter.updateStatus(set);
            System.out.println("Successful Tweet of "+set.getStatus());
        }

        private String getUserName() throws TwitterException{
            Twitter twitter = TwitterFactory.getSingleton();
            return twitter.getScreenName();
        }

        private int getFollowerCount()throws TwitterException{
            Twitter twitter = TwitterFactory.getSingleton();
            return twitter.getFollowersIDs(-1).getIDs().length;
        }

        private static String generateURL(){
            String url = "C:\\\\Users\\\\Scotts Gamer\\\\Desktop\\\\coffeepictures\\\\";
            url+=i+".jpg";
            return url;
        }

        public static void main(String[] args){
            twitterbot tweeter = new twitterbot();


            if (!debug){
                System.out.println();
                Scanner tweetget = new Scanner(System.in);
                while(i<=pictureNum) {
                    System.out.println("Check Picture Numbered "+i+" And Enter the Tweet You Want to Send:");
                    String tweet=tweetget.nextLine();
                    try {
                        sendTweet(tweet);
                        System.out.println(tweeter.getUserName()+" sent a tweet to "+tweeter.getFollowerCount()+" Followers on ");
                    } catch (TwitterException e) {
                        e.printStackTrace();
                    }
                    i++;
                    // forces an hour of waiting for the bot
                    try{
                        System.out.println("Sleeping For An Hour...");
                        Thread.sleep(60*60*1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }

        }


}