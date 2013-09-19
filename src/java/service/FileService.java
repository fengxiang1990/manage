package service;

import java.util.List;

import com.sina.sae.storage.SaeStorage;

public  class FileService  implements IFile{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String domin = "imgservice";
	SaeStorage storage = new SaeStorage("345wlkx234","i44m5y5j5lj4wjw2mlx5wxjwyz05zzyj043yxlh2","ImageService") ;
	@Override
	public int getFileNum() {
		// TODO Auto-generated method stub
		return  storage.getList(domin,"",1000000000,1).size();
	}
	@Override
	public List<String> getFileNames() {
		// TODO Auto-generated method stub
		return storage.getList(domin,"",1000000000,1);
	}



}
