package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class BoardsDDL {
	
	//getSelect (게시판 내역 가져오기)
		public static Vector<BoardsDTO> getTitle(String bbstitle, int limitNum, int listNum)
		{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String sql = "select * from bbs where bbstitle=? order by num desc limit ?, ?";
			Vector<BoardsDTO> data = new Vector<>();
			//conn = DBConnect.initConnection();
			conn = new DBConnect().getConn();
			try 
			{
				ps = conn.prepareStatement(sql);
				ps.setString(1, bbstitle);
				ps.setInt(2, limitNum);
				ps.setInt(3, listNum);
				//System.out.println(ps);
				rs = ps.executeQuery();
				//System.out.println(rs);
					while(rs.next())
					{
						BoardsDTO bb = new BoardsDTO();
						bb.setUserid(rs.getString("userid"));
						bb.setUsername(rs.getString("username"));
						bb.setWdate(rs.getString("wdate"));
						bb.setTitle(rs.getString("title"));
						bb.setContent(rs.getString("content"));
						bb.setCount(rs.getInt("count"));
						bb.setIschecked(rs.getInt("ischecked"));
						bb.setViews(rs.getInt("views"));
						data.add(bb);
						//System.out.println(data);
					}
			}
			catch(Exception e){e.printStackTrace();}
			finally
			{
				try
				{
					if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					if(conn!=null) conn.close();
				}
				catch(Exception e){}
			}
			return data;
		}
		
		//getSelect (게시판 내용 가져오기)
				public static Vector<BoardsDTO> getTitle(String bbstitle, int count)
				{
					Connection conn = null;
					PreparedStatement ps = null;
					ResultSet rs = null;
					
					String sql = "select * from bbs where bbstitle=? and count=?";
					Vector<BoardsDTO> data = new Vector<>();
					//conn = DBConnect.initConnection();
					conn = new DBConnect().getConn();
					try 
					{
						ps = conn.prepareStatement(sql);
						ps.setString(1, bbstitle);
						ps.setInt(2, count);
						//System.out.println(ps);
						rs = ps.executeQuery();
						//System.out.println(rs);
							while(rs.next())
							{
								BoardsDTO bb = new BoardsDTO();
								bb.setUserid(rs.getString("userid"));
								bb.setUsername(rs.getString("username"));
								bb.setWdate(rs.getString("wdate"));
								bb.setTitle(rs.getString("title"));
								bb.setContent(rs.getString("content"));
								bb.setIschecked(rs.getInt("ischecked"));
								bb.setViews(rs.getInt("views"));
								data.add(bb);
								//System.out.println(data);
							}
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(rs!=null) rs.close();
							if(ps!=null) ps.close();
							if(conn!=null) conn.close();
						}
						catch(Exception e){}
					}
					return data;
				}
		
		//select (공지사항 개수 가져오기)
				public static int getAllTitle(String bbstitle)
				{
					Connection conn = null;
					PreparedStatement ps = null;
					ResultSet rs = null;
					int allCount = 0;
					
					String sql = "select count(*) from bbs where bbstitle=?";
					//Vector<BoardsDTO> data = new Vector<>();
					//conn = DBConnect.initConnection();
					try 
					{
						conn = new DBConnect().getConn();
						ps = conn.prepareStatement(sql);
						ps.setString(1, bbstitle);
						rs = ps.executeQuery();
						
						while(rs.next())
						{
							allCount = rs.getInt(1);
						}
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(rs!=null) rs.close();
							if(ps!=null) ps.close();
							if(conn!=null) conn.close();
						}
						catch(Exception e){}
					}
					return allCount;
				}
		
				//bbs 테이블에 글 등록하는 메소드 (글 작성)
				public boolean insert(BoardsDTO dto)
				{
					Connection conn = null;
					PreparedStatement ps = null;
					int flag =0;
					
					try
					{
						//conn = DBConnect.initConnection();  //Connection 객체에서 conn 받아오기
						conn = new DBConnect().getConn();
						String query = "insert into bbs"
										+"(userid, username, title, content, bbstitle,  uip, count, ischecked)"
										+"values"
										+"(?,?,?,?,?,?,?,?)";
						ps = conn.prepareStatement(query);
						ps.setString(1, dto.getUserid());
						ps.setString(2, dto.getUsername());
						ps.setString(3, dto.getTitle());
						ps.setString(4, dto.getContent());
						ps.setString(5, dto.getBbstitle());
						ps.setString(6, dto.getUip());
						ps.setInt(7, dto.getCount());
						ps.setInt(8, dto.getIschecked());
						flag = ps.executeUpdate();
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(ps!=null) ps.close();
							if(conn!=null) conn.close();
						}
						catch(Exception e){}
					}
					if(flag>0) return true;  //성공
					else return false;  //실패
				}
				
				//게시판에 맞는 카운트 올리기
				public int countck(BoardsDTO dto)
				{
					Connection conn = null;
					PreparedStatement ps = null;
					ResultSet rs = null;
					int count=0;
					String sql = "select count from bbs where bbstitle=? order by num desc limit 1";
					
					try
					{
						//conn = DBConnect.initConnection();  //Connection 객체에서 conn 받아오기
						conn = new DBConnect().getConn();

						
						ps= conn.prepareStatement(sql);
						ps.setString(1, dto.getBbstitle());
						rs = ps.executeQuery();
						while(rs.next())
						{
							count=rs.getInt(1);
							//System.out.println(count);
						}
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(rs!=null) rs.close();
							if(ps!=null) ps.close();
							if(conn!=null) conn.close();
						}
						catch(Exception e){}
					}
					count+=1;
					return count;
				}
				
				//게시판 수정
				public boolean update(BoardsDTO dto)
				{
					Connection conn = null;
					PreparedStatement ps = null;
					int flag =0;
					try
					{
						conn = new DBConnect().getConn();
						String query ="";
						query = "update bbs set title=?, content=?, wdate=?, ischecked=? where bbstitle=? and count=?";
						ps = conn.prepareStatement(query);
						ps.setString(1, dto.getTitle());
						ps.setString(2, dto.getContent());
						ps.setString(3, dto.getWdate());
						ps.setInt(4, dto.getIschecked());
						ps.setString(5, dto.getBbstitle());
						ps.setInt(6, dto.getCount());
						
						flag = ps.executeUpdate();
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(ps!=null)ps.close();
							if(conn!=null)conn.close();
						}
						catch(Exception e){}
					}
					if(flag>0) return true;  //성공
					else return false;  //실패
				}
				
				//게시판 삭제
				public int delete(int count, String bbstitle)
				{
					Connection conn = null;
					PreparedStatement ps = null,ps2=null;
					int flag =0;
					try
					{
						conn = new DBConnect().getConn();
						String query = "";
						String query2 = "";
						query = "delete from bbs where count=? and bbstitle=? ";
						ps = conn.prepareStatement(query);
						ps.setInt(1, count);
						ps.setString(2, bbstitle);
						
						if(bbstitle.equals("notice")) query2 = "delete from ncomment where count=?";
						if(bbstitle.equals("information")) query2 = "delete from icomment where count=?";
						if(bbstitle.equals("free")) query2 = "delete from fcomment where count=?";
						if(bbstitle.equals("qanda")) query2 = "delete from qcomment where count=?";
						ps2 = conn.prepareStatement(query2);
						ps2.setInt(1, count);
						
						ps2.executeUpdate();
						flag = ps.executeUpdate();
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(ps2!=null)ps2.close();
							if(ps!=null)ps.close();
							if(conn!=null)conn.close();
						}
						catch(Exception e){}
					}
					return flag;
				}
				
				//게시판 이전내역
				public int before(String bbstitle,int count)
				{
					Connection conn = null;
					PreparedStatement ps = null;
					ResultSet rs = null;
					int bcount = 0;
					try
					{
						conn = new DBConnect().getConn();
						String query ="";

						query = "select count from bbs where bbstitle=? and count<? order by num desc limit 1;";
						ps = conn.prepareStatement(query);
						ps.setString(1, bbstitle);
						ps.setInt(2, count);
						
						rs = ps.executeQuery();
						while(rs.next())
						{
							bcount=rs.getInt(1);
							//System.out.println(count);
						}
						//System.out.println(bcount);
					}
					catch(Exception e)
					{
						e.printStackTrace();
						bcount=0;
					}
					finally
					{
						try
						{
							if(rs!=null)rs.close();
							if(ps!=null)ps.close();
							if(conn!=null)conn.close();
						}
						catch(Exception e){}
					}
					return bcount;
				}
				
				//게시판 다음내역
				public int after(String bbstitle,int count)
				{
					Connection conn = null;
					PreparedStatement ps = null;
					ResultSet rs = null;
					int bcount = 0;
					try
					{
						conn = new DBConnect().getConn();
						String query ="";

						query = "select count from bbs where bbstitle=? and count>? limit 1;";
						ps = conn.prepareStatement(query);
						ps.setString(1, bbstitle);
						ps.setInt(2, count);
						
						rs = ps.executeQuery();
						while(rs.next())
						{
							bcount=rs.getInt(1);
							//System.out.println(count);
						}
						//System.out.println(bcount);
					}
					catch(Exception e)
					{
						e.printStackTrace();
						bcount=0;
					}
					finally
					{
						try
						{
							if(rs!=null)rs.close();
							if(ps!=null)ps.close();
							if(conn!=null)conn.close();
						}
						catch(Exception e){}
					}
					return bcount;
				}
				
				//댓글 작성
				public boolean comment(BoardsDTO dto,String table)
				{
					Connection conn = null;
					PreparedStatement ps = null;
					int flag =0;
					
					try
					{
						//conn = DBConnect.initConnection();  //Connection 객체에서 conn 받아오기
						conn = new DBConnect().getConn();
						String query = "insert into "+table
										+"(count, userid, username, comment, uip, wdate)"
										+"values"
										+"(?,?,?,?,?,?)";
						ps = conn.prepareStatement(query);
						ps.setInt(1, dto.getCount());
						ps.setString(2, dto.getUserid());
						ps.setString(3, dto.getUsername());
						ps.setString(4, dto.getComment());
						ps.setString(5, dto.getUip());
						ps.setString(6, dto.getWdate());
						flag = ps.executeUpdate();
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(ps!=null) ps.close();
							if(conn!=null) conn.close();
						}
						catch(Exception e){}
					}
					if(flag>0) return true;  //성공
					else return false;  //실패
				}
				
				//getSelect (댓글 내용 가져오기)
				public static Vector<BoardsDTO> getComment(int count, int limitNum, int listNum, String table)
				{
					Connection conn = null;
					PreparedStatement ps = null;
					ResultSet rs = null;
					
					String sql = "select * from "+table+" where count=? order by num desc limit ?, ?";
					Vector<BoardsDTO> data = new Vector<>();
					//conn = DBConnect.initConnection();
					conn = new DBConnect().getConn();
					try 
					{
						ps = conn.prepareStatement(sql);
						ps.setInt(1, count);
						ps.setInt(2, limitNum);
						ps.setInt(3, listNum);
						//System.out.println(ps);
						rs = ps.executeQuery();
						//System.out.println(rs);
							while(rs.next())
							{
								BoardsDTO bb = new BoardsDTO();
								bb.setNum(rs.getInt("num"));
								bb.setUserid(rs.getString("userid"));
								bb.setUsername(rs.getString("username"));
								bb.setWdate(rs.getString("wdate"));
								bb.setComment(rs.getString("comment"));
								data.add(bb);
								//System.out.println(data);
							}
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(rs!=null) rs.close();
							if(ps!=null) ps.close();
							if(conn!=null) conn.close();
						}
						catch(Exception e){}
					}
					return data;
				}
				
				//select (댓글 총 개수 가져오기)
				public static int getAllComments(int count, String bbstitle)
				{
					Connection conn = null;
					ResultSet rs = null;
					PreparedStatement ps = null;
					int allCount = 0;
					String table = "";
					if(bbstitle.equals("notice"))table="ncomment";
					if(bbstitle.equals("information"))table="icomment";
					if(bbstitle.equals("free"))table="fcomment";
					if(bbstitle.equals("qanda"))table="qcomment";
					
					String sql = "select count(*) from "+table+" where count=?";
					//Vector<BoardsDTO> data = new Vector<>();
					//conn = DBConnect.initConnection();
					try 
					{
						conn = new DBConnect().getConn();
						ps = conn.prepareStatement(sql);
						ps.setInt(1, count);
						rs = ps.executeQuery();
						
						while(rs.next())
						{
							allCount = rs.getInt(1);
						}
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(rs!=null) rs.close();
							if(ps!=null) ps.close();
							if(conn!=null) conn.close();
						}
						catch(Exception e){}
					}
					return allCount;
				}
				
				//comment update
				public boolean comupdate(BoardsDTO dto,String table)
				{
					Connection conn = null;
					PreparedStatement ps = null;
					int flag =0;
					try
					{
						conn = new DBConnect().getConn();
						String query ="";
						query = "update "+table+" set comment=?, wdate=? where num=?";
						ps = conn.prepareStatement(query);
						ps.setString(1, dto.getComment());
						ps.setString(2, dto.getWdate());
						ps.setInt(3, dto.getNum());
						
						flag = ps.executeUpdate();
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(ps!=null)ps.close();
							if(conn!=null)conn.close();
						}
						catch(Exception e){}
					}
					if(flag>0) return true;  //성공
					else return false;  //실패
				}
				
				//comment delete
				public int comdelete(int num, String table)
				{
					Connection conn = null;
					PreparedStatement ps = null;
					int flag =0;
					try
					{
						conn = new DBConnect().getConn();
						String query ="";

						query = "delete from "+table+" where num=?";
						ps = conn.prepareStatement(query);
						ps.setInt(1, num);
						
						flag = ps.executeUpdate();
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(ps!=null)ps.close();
							if(conn!=null)conn.close();
						}
						catch(Exception e){}
					}
					return flag;
				}
				
				//Q&A게시판 접속
				public boolean watchqa(BoardsDTO dto,String user)
				{
					Connection conn = null;
					PreparedStatement ps = null;
					ResultSet rs = null;
					String userid = "";
					int ischecked =0;
					try
					{
						conn = new DBConnect().getConn();
						String query ="";
						query = "select * from bbs where count=? and bbstitle=?";
						ps = conn.prepareStatement(query);
						ps.setInt(1, dto.getCount());
						ps.setString(2, dto.getBbstitle());
						
						rs = ps.executeQuery();
						while(rs.next())
						{
							userid = rs.getString("userid");
							ischecked = rs.getInt("ischecked");
						}
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(rs!=null)rs.close();
							if(ps!=null)ps.close();
							if(conn!=null)conn.close();
						}
						catch(Exception e){}
					}
					if(ischecked==0) return true;
					else
					{
						if(userid.equals(user))return true;
						else return false;
					}
				}
				
				//조회수
				public void isview(String viewname, int count, String bbstitle)
				{
					Connection conn = new DBConnect().getConn();
					PreparedStatement ps = null,ps2=null,ps3=null,ps4=null;
					ResultSet rs = null , rs2 = null;
					String query ="";
					int num=0,view=0;
					try
					{
						query = "select count(*) from isviews where count=? and bbstitle=? and viewname=?";
						ps = conn.prepareStatement(query);
						ps.setInt(1,count);
						ps.setString(2, bbstitle);
						ps.setString(3, viewname);
						rs = ps.executeQuery();
						while(rs.next())
						{
							num=rs.getInt(1);
						}
						if(num==0)
						{
						query = "insert into isviews"
							+"(count, bbstitle, viewname)"
							+"values"
							+"(?,?,?)";
						ps2 = conn.prepareStatement(query);
						ps2.setInt(1, count);
						ps2.setString(2, bbstitle);
						ps2.setString(3, viewname);
						ps2.executeUpdate();
						
						query = "select views from bbs where count=? and bbstitle=?";
						ps3 = conn.prepareStatement(query);
						ps3.setInt(1,count);
						ps3.setString(2, bbstitle);
						rs2 = ps3.executeQuery();
						while(rs2.next())
						{
							view = rs2.getInt("views")+1;
						}
						
						query = "update bbs set views=? where count=? and bbstitle=?";
						ps4 = conn.prepareStatement(query);
						ps4.setInt(1, view);
						ps4.setInt(2, count);
						ps4.setString(3, bbstitle);
						ps4.executeUpdate();
						System.out.println("조회 기록이 없음");
						}
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(rs2!=null)rs2.close();
							if(rs!=null)rs.close();
							if(ps4!=null)ps4.close();
							if(ps3!=null)ps3.close();
							if(ps2!=null)ps2.close();
							if(ps!=null)ps.close();
							if(conn!=null)conn.close();
						}
						catch(Exception e){}
					}
				}
				
				//인기글 설정
				public static Vector<BoardsDTO> getLists(String what)
				{
					Connection conn = new DBConnect().getConn();
					PreparedStatement ps = null;
					ResultSet rs = null;
					String sql = "";
					if(what.equals("best")) sql = "select * from bbs where bbstitle!='qanda' order by views desc, wdate desc limit 0,6;";
					if(what.equals("recent")) sql = "select * from bbs where bbstitle!='qanda' order by wdate desc limit 0,5";
					Vector<BoardsDTO> data = new Vector<>();
					try 
					{
						ps = conn.prepareStatement(sql);
						rs = ps.executeQuery();
							while(rs.next())
							{
								BoardsDTO bb = new BoardsDTO();
								bb.setUsername(rs.getString("username"));
								bb.setTitle(rs.getString("title"));
								bb.setContent(rs.getString("content"));
								bb.setWdate(rs.getString("wdate"));
								bb.setViews(rs.getInt("views"));
								bb.setBbstitle(rs.getString("bbstitle"));
								bb.setCount(rs.getInt("count"));
								data.add(bb);
							}
					}
					catch(Exception e){e.printStackTrace();}
					finally
					{
						try
						{
							if(rs!=null) rs.close();
							if(ps!=null) ps.close();
							if(conn!=null) conn.close();
						}
						catch(Exception e){}
					}
					return data;
				}
}









