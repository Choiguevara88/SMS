package logic;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.BoardDao;
import dao.BuildingDao;
import dao.MemberDao;
import dao.ReserveDao;
import dao.RoomDao;

@Service
public class ProjectServiceImpl implements ProjectService {
   
   @Autowired
   private MemberDao memDao;
   @Autowired
   private BoardDao boDao;
   @Autowired
   private ReserveDao reDao;
   @Autowired
   private BuildingDao buDao;
   @Autowired
   private RoomDao roomDao;

   @Override
   public Member getMember(String id) {
      return memDao.select(id);
   }

   @Override
   public int boardcount(String searchType, String searchContent) {
      return boDao.count(searchType, searchContent);
   }

   @Override
   public List<Board> boardList(String searchType, String searchContent, Integer pageNum, int limit) {
      return boDao.list(searchType, searchContent, pageNum, limit);
   }

   @Override // board Write Method()
   public void boardWrite(Board board, HttpServletRequest request) {
      
      if (board.getImg1File() != null) { 
         String img = uploadImgCreate(board.getImg1File(),request);   
         if(img != null) board.setImg1(img); 
      }
      
      if (board.getImg2File() != null) { 
         String img = uploadImgCreate(board.getImg2File(),request);
         if(img != null) board.setImg2(img);
      }
      
      if (board.getImg3File() != null) { 
         String img = uploadImgCreate(board.getImg3File(),request);
         if(img != null) board.setImg3(img);
      }
      
      if (board.getImg4File() != null) { 
         String img = uploadImgCreate(board.getImg1File(),request);
         if(img != null) board.setImg4(img);
      }
      int num = boDao.maxNum();
      
      board.setbNo(++num);
      board.setRef(num);
      board.setRefLevel(0);
      boDao.insert(board);

   }

   @Override
   public Board getBoard(int num) {
      return boDao.getBoard(num);
   }

   @Override
   public void boardReply(Board board) {
      int num = boDao.maxNum();
      board.setbNo(++num);
      board.setRefLevel(board.getRefLevel()+1);
      boDao.insert(board);
   }

   @Override // board Update Method()
   public void boardUpdate(Board board, HttpServletRequest request) {   
      
      if (board.getImg1File() != null) { // img1  
         String img = uploadImgCreate(board.getImg1File(),request);   // img1 upload & img1Name setting 
         if(img != null) board.setImg1(img); // img1Name input
      }
      
      if (board.getImg2File() != null) { 
         String img = uploadImgCreate(board.getImg2File(),request);
         if(img != null) board.setImg2(img);
      }
      
      if (board.getImg3File() != null) { 
         String img = uploadImgCreate(board.getImg3File(),request);
         if(img != null) board.setImg3(img);
      }
      
      if (board.getImg4File() != null) { 
         String img = uploadImgCreate(board.getImg4File(),request);
         if(img != null) board.setImg4(img);
      }
      
      boDao.update(board);

   }

   @Override
   public void boardDelete(Integer num) {
      boDao.delete(num);
   }

   @Override
   public void updateReadCnt(Integer num) {

   }
   
   @Override
   public Reserve getReserve(Integer reNo) {
      return reDao.getReserve(reNo);
   }
   
   @Override
   public Room getRoom(Integer srNo) {
      return roomDao.getRoom(srNo);
   }
   

   @Override
   public List<Reserve> getReserveList(String id) {
      return reDao.getReserveList(id);
   }
   
   @Override
   public int reserveCount(String id, String searchType, String searchContent) {
      return reDao.count(id, searchType, searchContent);
   }
   
   @Override
   public List<Reserve> selectReserveList(String id, String searchType, String searchContent, Integer pageNum, int limit) {
      return reDao.list(id, searchType, searchContent, pageNum, limit);
   }
   
   @Override
   public void reserveCancel(Integer reNo, Integer reStat) {
      reDao.cancel(reNo, reStat);
   }
   
   @Override
   public int hostBuildCount(String hostName) {
      return buDao.hostBuildCnt(hostName);
   }
   
   @Override
   public Building selectHostReserveInfo(String hostName, Integer sNo) {
      return buDao.resInfo(hostName, sNo);
   }
   
   @Override
   public void reserveInsert(Reserve reserve) {
      reserve.setReNo(reserve.getSrNo() + (int)new Date().getTime());
      reDao.insert(reserve);
   }
   
   @Override
   public void reserveUpdate(Reserve reserve) {
      reDao.update(reserve);
   }

   private String uploadImgCreate(MultipartFile picture, HttpServletRequest request) { // imgUploadMethod()
      String uploadPath = request.getServletContext().getRealPath("/") + "/picture/"; // upload path setting
      Date date = new Date();
      String orgFile = date.getTime() + picture.getOriginalFilename(); // imgFileName setting
      try {
         picture.transferTo(new File(uploadPath + orgFile)); // new File(uploadPath + orgFile) : img upload complite
         return orgFile; // imgFileName Return
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   } // uploadImgCreate() end
   
   private String uploadImgCreate2(List<MultipartFile> pictures, HttpServletRequest request) { // imgUploadMethod()
      
      String uploadPath = request.getServletContext().getRealPath("/") + "/picture/"; // upload path setting
      Date date = new Date();
      String orgFile = "";
      
      for(MultipartFile picture : pictures) {      
         
         String fileName = date.getTime() + picture.getOriginalFilename(); // imgFileName setting
         
         try {
            picture.transferTo(new File(uploadPath + fileName)); // new File(uploadPath + orgFile) : img upload complite
            orgFile = orgFile + "|" + fileName;   
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      
      return orgFile;
   }

   @Override
   public void joinsms(Member member) {
      memDao.joinsms(member);
   }

   @Override
   public void updateMember(Member member) {
      memDao.updateMember(member);
   }

   @Override
   public int boardcount(Integer kind, int sNo) {
      return boDao.count(kind, sNo);
   }

   @Override
   public List<Board> boardList(Integer kind, int sNo, Integer pageNum, int limit) {
      return boDao.list(kind, sNo, pageNum, limit);
   }


   @Override
   public void insertRoom(Room room) {
      roomDao.insertRoom(room);
   }

   @Override
   public int hostReserveCount(String hostName, Integer sNo, String searchType, String searchContent) {
      return reDao.hostCount(sNo, hostName, searchType, searchContent);
   }

   @Override
   public List<Reserve> selectHostReserveList(Integer sNo, String hostName, String searchType, String searchContent, Integer pageNum, int limit) {
      return reDao.hostlist(sNo, hostName, searchType, searchContent, pageNum, limit);
   }

   @Override
   public List<Integer> hostHaveBuildsNo(String hostId) {
      return buDao.hostHaveBuildsNo(hostId);
   }

   @Override
   public void hostPaymentConfirm(Integer reNo) {
      reDao.hostPaymentConfirm(reNo);      
   }
   
   @Override
   public List<Board> boardList(Integer kind, int sNo) {
      return boDao.list(kind, sNo);
   }


   @Override
   public void buildingReg(Building building, HttpServletRequest request) {
      /*if (board.getImg1File() != null) { 
         String img = uploadImgCreate(board.getImg1File(),request);   
         if(img != null) board.setImg1(img); 
      }
      
      if (board.getImg2File() != null) { 
         String img = uploadImgCreate(board.getImg2File(),request);
         if(img != null) board.setImg2(img);
      }
      
      if (board.getImg3File() != null) { 
         String img = uploadImgCreate(board.getImg3File(),request);
         if(img != null) board.setImg3(img);
      }
      
      if (board.getImg4File() != null) { 
         String img = uploadImgCreate(board.getImg1File(),request);
         if(img != null) board.setImg4(img);
      }
      int num = boDao.maxNum();
      
      board.setbNo(++num);
      board.setRef(num);
      board.setRefLevel(0);

      boDao.insert(board);*/
      
      if(building.getsImg1File() != null) {
         String img1 = uploadImgCreate(building.getsImg1File(),request);
         if(img1 != null) building.setsImg1(img1);
      }
      
      if(!(building.getsImg2Files().isEmpty())) {
         String img2 = uploadImgCreate2(building.getsImg2Files(), request);
         if(img2 != null) building.setsImg2(img2);
      }
      
      int sNo = buDao.maxNum();
      building.setsNo(++sNo);
      /*로그인 정보를 받아줄 수 있을때 id 다시해야함*/
      String id = "id" + sNo;
      building.setId(id);
      String sType = listToString(building.getsTypeList());
      String sTag = listToString(building.getsTagList());
      String sInfoSub = listToString(building.getsInfoSubList());
      String sRule = listToString(building.getsRuleList());
      String sBHour = listToString(building.getsBHourList());
      int sStat = 0;
      building.setsType(sType);
      building.setsTag(sTag);
      building.setsInfoSub(sInfoSub);
      building.setsRule(sRule);
      building.setsBHour(sBHour);
      building.setsStat(sStat);
      System.out.println("service" + building);
      buDao.buRegist(building);
   }

   private String listToString(List<String> list) {
      String li = "";
      for (int i = 0 ; i < list.size() ; i++) {
         li += list.get(i) + "|";
      }
      return li;
   }

   @Override
   public List<Board> guestQuestionList() {
      return boDao.guestQuestionList();
   }
   
   @Override
   public List<Board> hostQuestionList() {
      return boDao.hostQuestionList();
   }

   @Override
   public List<Member> hostRegList() {
      return memDao.getHostRegList();
   }

   @Override
   public void hostRegister(String id) {
      memDao.hostRegister(id);
   }
   
   @Override
   public void becomeaHost(Member member, HttpServletRequest request) {
      if(member.getPicture() != null && !member.getPicture().isEmpty()) {
         uploadFileCreate(member.getPicture(), request);
         member.setPictureUrl(member.getPicture().getOriginalFilename());
      }
      memDao.becomeaHost(member);
   }
   
   private void uploadFileCreate(MultipartFile picture, HttpServletRequest request) {
      String uploadPath = request.getServletContext().getRealPath("/") + "/picture/";
      String orgFile = picture.getOriginalFilename();
      try {
         picture.transferTo(new File(uploadPath + orgFile));
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
   
} // ProjectServiceImpl end