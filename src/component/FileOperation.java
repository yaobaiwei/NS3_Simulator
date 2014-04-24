package component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {

	/**
	 * 
	 * @param file_path  //文件路径+文件名
	 * @param stream //要写入的字符串
	 * @oaram add //true表示追加新内容，false表示更新
	 * function 输入想要生成的文件路径，生成最终的文件
	 */
	public void Inputfile(String file_path, String stream, boolean add) throws IOException{
		File file = new File(file_path);
		if(!file.exists())
			file.createNewFile();
		
        FileWriter fileWritter = new FileWriter(file,add);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        StringBuffer sb=new StringBuffer();
        sb.append(stream);
        bufferWritter.write(sb.toString());
        bufferWritter.close();
	}
	
	
	/**
	 * 
	 * @param path   //文件路径
	 * @return   文件中的内容 
	 * @throws IOException
	 * function 输入文件的绝对路径，输出String型的文件内容
	 */
    public String FileRead(String path) throws IOException{

        File file=new File(path);
        
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();

        BufferedReader reader = null; 
        reader = new BufferedReader(new FileReader(file)); 
        
        StringBuffer sb=new StringBuffer();
        String tempString = null;
        
        while ((tempString = reader.readLine()) != null){ 
        	sb.append(tempString+"\n");   
        } 
        reader.close(); 
        
        return sb.toString();
    }
    
}
