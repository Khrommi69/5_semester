package Interfaces;

//описывает методы для выгрузки буфера в текстовый файл

public interface IBufferStorable {
	//сохраняет буфер в файл в одну строку
	public void SaveOneLine(String filename);
	//сохраняет буфер в файл по одному элементу в строке
	public void SaveSeparateLines (String filename);
}