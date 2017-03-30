package org.aritra.restful.Messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.aritra.restful.Messenger.database.DatabaseClass;
import org.aritra.restful.Messenger.model.Message;

public class MessageService {
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	public MessageService()
	{
		messages.put(1L, new Message(1,"Hello World","Aritra"));
		messages.put(2L, new Message(2,"Hello Jersey","Abhishek"));
	}
	public List<Message> getAllMessages()
	{
		return new ArrayList<Message>(messages.values());
	}
	public List<Message> getAllMessagesForYear(int year)
	{
		List<Message> thisYearMessages = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message m : messages.values())
		{
			cal.setTime(m.getCreated());
			if(cal.get(Calendar.YEAR)==year)
			{
				thisYearMessages.add(m);
			}
		}
		return thisYearMessages;
	}
	public List<Message> getAllMessagesPaginated(int start,int size)
	{
		List<Message> messagesInPage = new ArrayList<Message>(messages.values());
		if(start+size>messagesInPage.size()) return new ArrayList<Message>();
		return messagesInPage.subList(start, start+size);
	}
	public Message getMessage(long id)
	{
		return messages.get(id);
	}
	public Message addMessage(Message message)
	{
		message.setId(messages.size()+1);
		messages.put(message.getId(),message);
		return message;
	}
	public Message updateMessage(Message message)
	{
		if(message.getId()<=0)
			return null;
		else
		{
			messages.put(message.getId(),message);
		}
		return message;
	}
	public Message removeMessage(long id)
	{
		return messages.remove(id);
	}

}
