package com.bookshop01.admin.order.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bookshop01.member.vo.MemberVO;
import com.bookshop01.order.vo.OrderVO;

@Repository("adminOrderDAO")
public class AdminOrderDAOImpl  implements AdminOrderDAO{
	@Autowired
	private SqlSession sqlSession;
	
	public ArrayList<OrderVO> selectNewOrderList(Map condMap) throws DataAccessException{
		
		//디버그
		System.out.println("DAO부분---------------------------");
		System.out.println(condMap);		
		
		ArrayList<OrderVO>  orderList=(ArrayList)sqlSession.selectList("mapper.admin.order.selectNewOrderList",condMap);
		
		//디버그
		System.out.println("DAO부분---------------------------");
		System.out.println(orderList);
		
		
		
		
		return orderList;
	}
	public void  updateDeliveryState(Map deliveryMap) throws DataAccessException{
		sqlSession.update("mapper.admin.order.updateDeliveryState",deliveryMap);
	}
	
	public ArrayList<OrderVO> selectOrderDetail(int order_id) throws DataAccessException{
		ArrayList<OrderVO> orderList=(ArrayList)sqlSession.selectList("mapper.admin.order.selectOrderDetail",order_id);
		return orderList;
	}


	public MemberVO selectOrderer(String member_id) throws DataAccessException{
		MemberVO orderer=(MemberVO)sqlSession.selectOne("mapper.admin.order.selectOrderer",member_id);
		return orderer;
		
	}
	
	// condMap 인자로 추가. AdminOrderDAO.java도 고치는거 주의
    public int selectTotArticles(Map condMap) {
        return sqlSession.selectOne("mapper.admin.order.selectTotArticles", condMap);
        
    } 
    
    //주문 삭제 기능 추가
    public void removeOrder(int order_id) throws DataAccessException {
    	 sqlSession.delete("mapper.admin.order.removeOrder",order_id);
    }
    

    

}
