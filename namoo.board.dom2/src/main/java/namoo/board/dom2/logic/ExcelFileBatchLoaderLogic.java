/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 1. 10.
 */
package namoo.board.dom2.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import namoo.board.dom2.entity.user.BoardUser;
import namoo.board.dom2.lifecycle.BoardServiceLifecycler;
import namoo.board.dom2.service.BoardUserService;
import namoo.board.dom2.service.ExcelFileBatchLoader;
import namoo.board.dom2.util.exception.NamooBoardException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileBatchLoaderLogic implements ExcelFileBatchLoader {
    //
    private final String TABLE_TEAM = "BoardTeam";
    private final int CONTENTS_USER_START_ROW = 5; 
    private final int CONTENTS_START_CELL_SEQ = 1;
    
    private BoardUserService service;
    
    public ExcelFileBatchLoaderLogic(BoardServiceLifecycler serviceLifecycler) {
        //
        this.service = serviceLifecycler.getBoardUserService();
    }
    
    //--------------------------------------------------------------------------
    
    @Override
    public boolean registerServiceUsers(File file) {
        // 
        List<BoardUser> users = service.findAllUsers();
        if (users.size() > 0) {
            return false;
        }
        
        try {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            
            List<String> userEmails = new ArrayList<String>();
            int rowSeq = CONTENTS_USER_START_ROW;
            
            // DCBoardUser 객체 생성하여 저장
            while (rowSeq <= sheet.getLastRowNum()) {
                //
                Row row = sheet.getRow(rowSeq);
                rowSeq++;
                
                if (row == null) {
                    break;
                }
                
                Cell cell = row.getCell(CONTENTS_START_CELL_SEQ);
                if (cell == null || cell.getStringCellValue().isEmpty() 
                        || cell.getStringCellValue().equals(TABLE_TEAM)) {
                    break;
                }
                
                int cellSeq = CONTENTS_START_CELL_SEQ;
                String userEmail = row.getCell(cellSeq).getStringCellValue();
                String userName = row.getCell(cellSeq + 1).getStringCellValue();
                String phoneNumber = row.getCell(cellSeq + 2).getStringCellValue();
                
                BoardUser user = new BoardUser(userEmail, userName, phoneNumber);
                service.registerUser(user);
                
                userEmails.add(userEmail);
            }
            
            // DCBoardTeam/Member 객체 생성하여 저장
            while (rowSeq <= sheet.getLastRowNum()) {
                //
                Row row = sheet.getRow(rowSeq);
                rowSeq++;
                
                if (row == null) {
                    continue;
                }
                
                Cell cell = row.getCell(CONTENTS_START_CELL_SEQ);
                
                if (cell == null || cell.getStringCellValue().isEmpty()) {
                    continue;
                }
                
                if ( cell.getStringCellValue().equals(TABLE_TEAM)) {
                    rowSeq++;
                    continue;
                }
                
                int cellSeq = CONTENTS_START_CELL_SEQ;
                String teamName = row.getCell(cellSeq).getStringCellValue();
                String adminEmail= row.getCell(cellSeq + 1).getStringCellValue();
                
                String teamUsid = service.registerBoardTeam(teamName, adminEmail);
                
                // Team member로 등록할 User 목록에서 admin은 제외 
                for (String email : userEmails) {
                    if (email.equals(adminEmail)) {
                        userEmails.remove(email);
                        break;
                    }
                }
                
                service.addToBoardTeam(teamUsid, userEmails);
            }
            return true;
            
        } catch (InvalidFormatException | IOException | NullPointerException e) {
            // 
            e.printStackTrace();
            throw new NamooBoardException("Team 엑셀파일 로드중 에러가 발생했습니다.");
        }
    }
    
}
