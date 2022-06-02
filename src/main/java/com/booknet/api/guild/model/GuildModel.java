package com.booknet.api.guild.model;

import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.feed.model.GuildNewsModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "guilds")
public class GuildModel {

    @Id
    String _id;

    @NotEmpty
    @Size(max = 20)
    String name;

    @NotNull
    @Size(max = 120)
    String description;

    @DBRef
    List<AppUser> members = new ArrayList<>();

    @DBRef
    List<GuildNewsModel> news = new ArrayList<>();

    public GuildModel() {
    }

    public GuildModel(String _id, String name) {
        this._id = _id;
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AppUser> getMembers() {
        return members;
    }

    public void setMembers(List<AppUser> members) {
        this.members = members;
    }

    public List<GuildNewsModel> getNews() {
        return news;
    }

    public void setNews(List<GuildNewsModel> news) {
        this.news = news;
    }

    public boolean isContainUser(AppUser user) {
        return this.getMembers().contains(user);
    }

    public void addMember(AppUser user) {
        if (user == null) return;

        List<AppUser> members = this.getMembers();
        if (!members.contains(user)) {
            members.add(user);
        }
    }

    public void removeMember(AppUser user) {
        if (user == null) return;

        List<AppUser> members = this.getMembers();
        members.remove(user);
    }

    public boolean isContainNews(GuildNewsModel news) {
        return this.getNews().contains(news);
    }

    public void addNews(GuildNewsModel news) {
        if (news == null) return;

        List<GuildNewsModel> listNews = this.getNews();
        if (!listNews.contains(news)) {
            listNews.add(news);
            this.setNews(listNews);
        }
    }

    public void removeNews(GuildNewsModel news) {
        if (news == null) return;

        List<GuildNewsModel> listNews = this.getNews();
        if (listNews.contains(news)) {
            listNews.remove(news);
            this.setNews(listNews);
        }
    }
}