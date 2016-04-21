package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import entity.Students;
import service.StudentsDao;
import serviceimpl.StudentsDaoImpl;

//ѧ��action��
public class StudentsAction extends SuperAction {


	private static final long serialVersionUID = 1L;
	
	//��ѯ����ѧ���Ķ���
	public String query()
	{
		StudentsDao sdao=new StudentsDaoImpl();
		List<Students> list=sdao.queryAllStudents();
		//�Ž�session��
		if(list.size()>0&&list!=null)
		{
			session.setAttribute("students_list", list);
			//return "Students_query_success";
		}
		return "query_success";
	}
	
	//ɾ��ѧ������
	public String delete()
	{
		StudentsDao sdao=new StudentsDaoImpl();
		String sid=request.getParameter("sid");
		sdao.deleteStudents(sid);
		return "delete_success";
		
	}
	
	//�޸�ѧ�����϶���
	public String modify()
	{
		String sid=request.getParameter("sid");
		StudentsDao sdao=new StudentsDaoImpl();
		Students s=sdao.queryStudentsBySid(sid);
		
		session.setAttribute("modify_students", s);
		return "modify_success";
		
	}
	
	//���һ��ѧ��
	public String add() throws Exception
	{
		StudentsDao sdao=new StudentsDaoImpl();
		Students s=new Students();
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		s.setBirthday(sdf.parse(request.getParameter("birthday")));
		s.setAddress(request.getParameter("address"));
		sdao.addStudents(s);
		return "add_success";
	}
	
	//�����޸ĺ��ѧ�����϶���
	public String save() throws Exception
	{
		StudentsDao sdao=new StudentsDaoImpl();
		Students s=new Students();
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		s.setBirthday(sdf.parse(request.getParameter("birthday")));
		s.setAddress(request.getParameter("address"));
		sdao.updateStudents(s);
		return "sava_success";
	}

	
	

}
