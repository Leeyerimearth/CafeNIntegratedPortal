package com.phoenix.mvc.service.mail.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phoenix.mvc.common.Event;
import com.phoenix.mvc.service.cafe.CafeManageDao;
import com.phoenix.mvc.service.cafe.CafeMemberDao;
import com.phoenix.mvc.service.cafe.CafePostDao;
import com.phoenix.mvc.service.cafe.CafePostService;
import com.phoenix.mvc.service.domain.Account;
import com.phoenix.mvc.service.domain.Board;
import com.phoenix.mvc.service.domain.Mail;
import com.phoenix.mvc.service.domain.Post;
import com.phoenix.mvc.service.domain.Reply;
import com.phoenix.mvc.service.mail.MailDao;
import com.phoenix.mvc.service.mail.MailService;
import com.phoenix.mvc.service.user.UserDao;

@Service
@Transactional
public class MailServiceImpl implements MailService {

	@Autowired
	@Qualifier("mailDaoImpl")
	private MailDao	mailDao;
	
	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao	userDao;
	
	public MailServiceImpl() {
		System.out.println(getClass().getName() + "default Constuctor");
	}

	@Override
	public Map<String, Object> getAllAccountMailList(List<Account> accountList, int currentPage) throws MessagingException, FileNotFoundException, IOException {
		return mailDao.getAllAccountMailList(accountList, currentPage);
	}
	
	@Override
	public Map<String, Object> getMailList(Account account, int currentPage) throws Exception {
		return mailDao.getMailList(account, currentPage);
	}

	@Override
	public Map<String, Object> getMail(Account account, int mailNo) throws Exception {
		return mailDao.getMail(account, mailNo);
	}

	@Override
	public boolean addMailAccount(Account account) throws Exception {
		if(mailDao.accountVaildationCheck(account)) {
			return userDao.addMailAccount(account);
		}
		
		return false;
	}

	@Override
	public boolean sendMail(Account account, Mail mail) throws MessagingException {
		
		if(account.getAccountDomain().contains("gmail")) {
			mailDao.sendGmail(account, mail);
		}else if(account.getAccountDomain().contains("naver")){
			mailDao.sendNaver(account, mail);
		}else {
			mailDao.sendDaum(account, mail);
		}
		
		return true;
	}

	@Override
	public Map<String, Object> getAllAccountSentMailList(List<Account> accountList, int currentPage) throws FileNotFoundException, MessagingException, IOException {
		return mailDao.getAllAccountSentMailList(accountList, currentPage);
	}

	@Override
	public Map<String, Object> getAllAccountFlagMailList(List<Account> accountList, int currentPage) throws FileNotFoundException, MessagingException, IOException {
		return mailDao.getAllAccountFlagMailList(accountList, currentPage);
	}

	@Override
	public Map<String, Object> getAllAccountDeletedMailList(List<Account> accountList, int currentPage)	throws FileNotFoundException, MessagingException, IOException {
		return mailDao.getAllAccountDeletedMailList(accountList, currentPage);
	}
	
	@Override
	public Map<String, Object> getSentMail(Account account, int mailNo) throws Exception {
		return mailDao.getSentMail(account, mailNo);
	}
	
	@Override
	public Map<String, Object> getTrashMail(Account account, int mailNo) throws Exception {
		return mailDao.getTrashMail(account, mailNo);
	}

	@Override
	public boolean setSeenMail(List<Map<String, Object>> mailInfoList, List<Account> accountList)	throws FileNotFoundException, MessagingException, IOException {
		return mailDao.setSeenMail(mailInfoList, accountList);
	}

	@Override
	public boolean setUnSeenMail(List<Map<String, Object>> mailInfoList, List<Account> accountList) throws FileNotFoundException, MessagingException, IOException {
		return mailDao.setUnSeenMail(mailInfoList, accountList);
	}
	
	@Override
	public boolean setFlagMail(List<Map<String, Object>> mailInfoList, List<Account> accountList) throws FileNotFoundException, MessagingException, IOException {
		return mailDao.setFlagMail(mailInfoList, accountList);
	}

	@Override
	public boolean setUnFlagMail(List<Map<String, Object>> mailInfoList, List<Account> accountList)	throws FileNotFoundException, MessagingException, IOException {
		return mailDao.setUnFlagMail(mailInfoList, accountList);
	}

	@Override
	public boolean trashMail(List<Map<String, Object>> mailInfoList, List<Account> accountList) throws FileNotFoundException, MessagingException, IOException {
		return mailDao.trashMail(mailInfoList, accountList);
	}

	@Override
	public boolean deleteMail(List<Map<String, Object>> mailInfoList, List<Account> accountList) throws FileNotFoundException, MessagingException, IOException {
		return mailDao.deleteMail(mailInfoList, accountList);
	}
	
	@Override
	public Map<String, Object> getBoxMailCount(List<Account> accountList) throws MessagingException, FileNotFoundException, IOException {
		return mailDao.getBoxMailCount(accountList);
	}

	@Override
	public boolean inboxMail(List<Map<String, Object>> mailInfoList, List<Account> accountList) throws MessagingException, FileNotFoundException, IOException {
		return mailDao.inboxMail(mailInfoList, accountList);
	}
}
