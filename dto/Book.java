package dto;

public class Book {
    private int id;
    private int srNo;
    private String bookName;
    private String authorName;
    private int bookQty;

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    public void setSrNo(int srNo){
        this.srNo=srNo;
    }
    public int getSrNo(){
        return srNo;
    }

    public void setBookName(String bookName){
        this.bookName=bookName;
    }
    public String getBookName(){
        return bookName;
    }
    
    public void setAuthorName(String authorName){
        this.authorName=authorName;
    }
    public String getAuthorName(){
        return authorName;
    }
    public int getBookQty(){
        return bookQty;
    }
    public void setBookQty(int bookQty){
        this.bookQty=bookQty;
    }
}
