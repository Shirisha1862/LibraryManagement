package dto;

public class BookingDetails {
    public int id;
    public int stdId;
    public int bookId;
    public String bookName;
    public String authorName;
    public int qty;
    public int srNo;
    
    public void setBookId(int bookId){
        this.bookId=bookId;
    }
    public int getBookId(){
        return bookId;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    public void setStdId(int stdId){
        this.stdId=stdId;
    }
    public int getstdId(){
        return stdId;
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
    public void setQty(int qty){
        this.qty=qty;
    }
    public int getQty(){
        return qty;
    }
    public void setSrNo(int srNo){
        this.srNo=srNo;
    }
    public int getSrNo(){
        return srNo;
    }
}
