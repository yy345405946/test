

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
//https://github.com/yy345405946/team_building.git
public class WeiXinServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		// TOKEN ������΢��ƽ̨����ģʽ�����õ�Ŷ
		public static final String TOKEN = "shhczx";

		/**
		 * ����΢�ŷ�������֤
		 * 
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			String signature = request.getParameter("signature");// ΢�ż���ǩ��
			String timestamp = request.getParameter("timestamp");// ʱ���
			String nonce = request.getParameter("nonce");// �����
			String echostr = request.getParameter("echostr");// ����ַ���

			System.out.println("��֤URL�Ƿ���ȷ");
			// ��дtotring�������õ�����������ƴ���ַ���
			List<String> list = new ArrayList<String>(3) {
				private static final long serialVersionUID = 2621444383666420433L;

				public String toString() {
					return this.get(0) + this.get(1) + this.get(2);
				}
			};
			list.add(TOKEN);
			list.add(timestamp);
			list.add(nonce);
			Collections.sort(list);// ����
			System.out.println(list.toString());
			String tmpStr = new DigestUtils().sha1Hex(list.toString());//(list.toString());// SHA-1����
			Writer out = response.getWriter();
			if (signature.equals(tmpStr)) {
				out.write(echostr);// ������֤�ɹ������������
			} else {
				out.write("");
			}
			out.flush();
			out.close();
		}
}
