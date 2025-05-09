package weather;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.*;
 
public class Weather__application implements ActionListener 
{
	static JTextField t1 ;
	JButton b1;
	static JFrame f ;
	static JLabel w1,h1,temp1,temp2,w_type,time,date;
	static ImageIcon cloudy ;
	public Weather__application() 
	{
		f = new JFrame("Weather Fetcher");
		f.getContentPane().setForeground(new Color(1, 1,1));
		
		JLabel citylabel = new JLabel("Enter city name :");
		citylabel.setBounds(50,10,100,20);
		citylabel.setForeground(Color.WHITE);
		
		t1 = new JTextField(12);
		t1.setBounds(150,10,150,20);
		
		//Search icon
		ImageIcon search = new ImageIcon("glass.png");
		Image s_img = search.getImage().getScaledInstance(23,23,Image.SCALE_SMOOTH);
		ImageIcon search_s  = new ImageIcon(s_img);
		JLabel s = new JLabel(search_s);
		s.setBounds(305,9,23,23);
		
		b1  = new JButton("Search");
		b1.setBounds(150,40,100,25);
		b1.addActionListener(this);
		
		f.add(citylabel);
		f.add(t1);
		f.add(s);
		f.add(b1);
		f.getContentPane().setBackground(new Color(0,0,0));
		
		
		f.setSize(400,120);
		f.setResizable(false);
		f.setLayout(null);
		f.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		
		String str = t1.getText();
		String str1=str.substring(0, 1).toUpperCase()+str.substring(1).toLowerCase();
		
		JLabel city_name = new JLabel();
		city_name.setText(str1);
		city_name.setBounds(15,100,200,25);
		city_name.setForeground(Color.WHITE);
		city_name.setFont(new Font("Britannic Bold",Font.PLAIN,25));
		f.add(city_name);

		//wind_speed icon
		ImageIcon wind = new ImageIcon("wind.png");
		Image wind_sp = wind.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon wind_speed = new ImageIcon(wind_sp);
		JLabel windspeed=new JLabel(wind_speed);
		windspeed.setBounds(32,490,50,50);
		f.add(windspeed);
		
		JLabel wind_l = new JLabel("wind");
		wind_l.setForeground(Color.white);
		wind_l.setBounds(37,539,50,10);
		wind_l.setFont(new Font(Font.MONOSPACED,Font.PLAIN,12));
		f.add(wind_l);
		
		w1=new JLabel("");
		w1.setForeground(Color.white);
		w1.setBounds(22,565,100,20);
		w1.setFont(new Font("Times New Roman",Font.PLAIN,15));
		f.add(w1);
	
		
		temp1=new JLabel("");
		temp1.setBounds(135,280, 300, 50);
		temp1.setForeground(Color.WHITE);
		temp1.setFont(new Font("Arial", Font.BOLD, 40));
		f.add(temp1);
		
		w_type=new JLabel("");
		w_type.setBounds(148,310, 300, 50);
		w_type.setForeground(Color.WHITE);
		w_type.setFont(new Font("Arial", Font.BOLD, 25));
		f.add(w_type);
		
		temp2=new JLabel("");
		temp2.setBounds(148,348, 300, 50);
		temp2.setForeground(Color.WHITE);
		temp2.setFont(new Font("Arial", Font.BOLD, 12));
		f.add(temp2);
		
		//humidity icon
		ImageIcon humid = new ImageIcon("humidity.png");
		Image humidity = humid.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon resized_hum = new ImageIcon(humidity);
		JLabel h_label=new JLabel(resized_hum);
		h_label.setBounds(300,490,50,50);
		f.add(h_label);
		
		JLabel hum_l = new JLabel("Humidity");
		hum_l.setForeground(Color.white);
		hum_l.setBounds(295,540,100,20);
		hum_l.setFont(new Font(Font.MONOSPACED,Font.PLAIN,12));
		f.add(hum_l);
		
		h1=new JLabel("");
		h1.setBounds(303, 565, 100, 20);
		h1.setForeground(Color.WHITE);
		h1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		f.add(h1);
		
		try 
		{
			JSONObject location_data = getLocation(t1.getText());
			double latitude = (double)location_data.get("latitude");
			double longitude = (double)location_data.get("longitude");
			
			display_weather(latitude,longitude);
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		
			
		f.setSize(400,650);
		f.setResizable(false);
		f.revalidate();
		
		
	}
	
	public static void main(String[] args)
	{
		new Weather__application();
		
	}
	
	private static JSONObject getLocation(String city)
	{
		city = city.replaceAll(" ","+");
		
		String url= "https://geocoding-api.open-meteo.com/v1/search?name="+city+"&count=1&language=en&format=json";
		
		try
		{
			//Fetching the API response of the link
			HttpURLConnection apiConnection = fetch_Api_Response(url);
			
			//checking the API response
			if(apiConnection.getResponseCode() != 200)//200 means that the connection is successful
			{
				System.out.println("Error: Could not connect to API");
				return null;
			}
			
			//read and convert the response into String
			String jsonResponse = read_Api_Response(apiConnection);
			
			JSONParser parser = new JSONParser(); //Convert the string into JSON object
			JSONObject result = (JSONObject)parser.parse(jsonResponse);
			
			
			//Retrieve location data
			JSONArray location_data = (JSONArray) result.get("results");
			return (JSONObject) location_data.get(0);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static HttpURLConnection fetch_Api_Response(String urlStr)
	{
		try
		{
			
			//creating a connection
			URL url = new URI((urlStr)).toURL();
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			//setting request method to GET
			conn.setRequestMethod("GET");
			
			return conn;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;  //return null if connection was not established
	}
	
	
	private static String read_Api_Response(HttpURLConnection api_Connection)
	{
		try
		{
			//create a StringBuilder object to store the JSON result
			StringBuilder JSONresult = new StringBuilder();
			
			Scanner scanner = new Scanner(api_Connection.getInputStream());
			
			
			while(scanner.hasNext())
			{
				JSONresult.append(scanner.nextLine());     //append the current line to the StringBuilder
					
			}
			
			//Close the scanner object to release the resources
			scanner.close();
			
			return  JSONresult.toString();//return the JSON result as String
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return null;   //return null if any error has occurred
	}
	
	private static void  display_weather(double latitude, double longitude)
	{
		try
		{
			String url = "https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+
								longitude+"&current=temperature_2m,relative_humidity_2m,apparent_temperature,wind_speed_10m,weather_code,is_day";
			
			HttpURLConnection apiConnection = fetch_Api_Response(url);
			
			if(apiConnection.getResponseCode()!=200)  //200 means that the connection is successful
			{
				System.out.println("Error: Could not connect to API");
				return ;
			}
			
			//read the response from API and convert it to String
			String jsonResponse = read_Api_Response(apiConnection);
			
			JSONParser parser = new JSONParser(); //Convert the string into JSON object
			JSONObject json_obj = (JSONObject)parser.parse(jsonResponse);
			JSONObject current_weather = (JSONObject) json_obj.get("current");
			
			
			String time=(String)current_weather.get("time");
			date = new JLabel();
			date.setText(time);
			date.setBounds(15,120,200,25);
			date.setForeground(Color.WHITE);
			date.setFont(new Font("Arial",Font.BOLD,12));
			f.add(date);
			
			double temperature = (double) current_weather.get("temperature_2m");
			temp1.setText(temperature + " °C");
			
			long humidity= (long)current_weather.get("relative_humidity_2m");
			h1.setText(humidity + " %");
			
			
			double windspeed = (double)current_weather.get("wind_speed_10m");
			w1.setText(windspeed + " km/h");
			
			double appar_temp = (double)current_weather.get("apparent_temperature");
			temp2.setText("Feels like  "+ appar_temp +" °C");
			
			long weather_code = (long)current_weather.get("weather_code");
			long is_day = (long)current_weather.get("is_day");
			if(weather_code==0)
			{
				if(is_day==1)
				{
					w_type.setText("Sunny");
					w_type.setBounds(160,320, 300, 50);
					f.getContentPane().setBackground(new Color(255,229,76));
					get_image("sun.png");
				}
				else
				{
					w_type.setText("Clear");
					w_type.setBounds(160,320, 300, 50);
					f.getContentPane().setBackground(new Color(84,65,109));
					get_image("night-moon.png");
				}
			}
			
			if(weather_code==1 || weather_code==2 || weather_code==3)
			{
				if(is_day==1)
				{
					w_type.setText("Partly Cloudy");	
					w_type.setBounds(125,320, 300, 50);
					f.getContentPane().setBackground(new Color(161,224,233));
					get_image("cloudy-day.png");
				}
				else
				{
					w_type.setText("Partly Cloudy");
					w_type.setBounds(125,320, 300, 50);
					f.getContentPane().setBackground(new Color(0,104,212));
					get_image("partly-cloudy-night.png");
				}
			}
			if(weather_code==61 || weather_code==63 || weather_code==65 || weather_code==80 || weather_code==81 || weather_code==82)
			{
				w_type.setText("Rainy");
				w_type.setBounds(162,320, 300, 50);
				f.getContentPane().setBackground(new Color(63,49,74));
				get_image("rainy-day.png");
			}
			if(weather_code==71 || weather_code==73 || weather_code==75 || weather_code==77 || weather_code==85 || weather_code==86)
			{
				w_type.setText("Snowy");
				w_type.setBounds(159,320, 300, 50);
				f.getContentPane().setBackground(new Color(160,230,236));
				get_image("snowy.png");
			}
			if(weather_code==45 || weather_code==48)
			{
				w_type.setText("Foggy");
				w_type.setBounds(160,320, 300, 50);
				f.getContentPane().setBackground(new Color(144,144,128));
				get_image("mist.png");
			}
			if(weather_code==95 || weather_code==96 || weather_code==99)
			{
				w_type.setText("ThunderStorm");
				w_type.setBounds(119,320, 300, 50);
				f.getContentPane().setBackground(new Color(63,87,129));
				get_image("thunderstorm.png");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static void get_image(String img)
	{
		ImageIcon cloudy = new ImageIcon(img);
		Image clo = cloudy.getImage().getScaledInstance(130,130,Image.SCALE_SMOOTH);
		ImageIcon cloudy_day  = new ImageIcon(clo);
		JLabel cd = new JLabel(cloudy_day);
		cd.setBounds(125, 140,150, 150);
		f.add(cd);
	}
	
}
