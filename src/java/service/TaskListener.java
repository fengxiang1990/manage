package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sina.sae.storage.SaeStorage;

import dao.ImageDao;

public class TaskListener implements ServletContextListener{

	private Timer timer;
	private TimerTask  task;
	private String domain = "imgservice";
	SaeStorage storage = new SaeStorage("345wlkx234","i44m5y5j5lj4wjw2mlx5wxjwyz05zzyj043yxlh2","ImageService") ;
	ImageDao dao  = new ImageDao();
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		timer = new Timer();
		task  =new UpdateDataTask();
		timer.schedule(task,0,1000*10);
	}
    class UpdateDataTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			List<String> filenames= storage.getList(domain,"",1000000000,1);
			List<String> newdates = new ArrayList<String>();
			for(String pic_name:filenames){
				if(!getAllFileNames().contains(pic_name)){
					//newdates.add(pic_name);
					dao.addImageInfo(pic_name,0,"admin");
				}
			}

		}   	
    }
    public List<String> getAllFileNames(){
    	return dao.getAllFileName();
    }
}
