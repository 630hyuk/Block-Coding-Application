package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the UserData type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "UserData", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class UserData implements Model {
  public static final QueryField ID = field("UserData", "id");
  public static final QueryField EMAIL = field("UserData", "email");
  public static final QueryField PW = field("UserData", "pw");
  public static final QueryField NICKNAME = field("UserData", "nickname");
  public static final QueryField PROGRESS = field("UserData", "progress");
  public static final QueryField REGISTER_STAMP = field("UserData", "registerStamp");
  public static final QueryField LAST_LOGIN_STAMP = field("UserData", "lastLoginStamp");
  public static final QueryField PHONE = field("UserData", "phone");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="AWSEmail") String email;
  private final @ModelField(targetType="String") String pw;
  private final @ModelField(targetType="String") String nickname;
  private final @ModelField(targetType="String") String progress;
  private final @ModelField(targetType="AWSDateTime") Temporal.DateTime registerStamp;
  private final @ModelField(targetType="AWSDateTime") Temporal.DateTime lastLoginStamp;
  private final @ModelField(targetType="AWSPhone") String phone;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getEmail() {
      return email;
  }
  
  public String getPw() {
      return pw;
  }
  
  public String getNickname() {
      return nickname;
  }
  
  public String getProgress() {
      return progress;
  }
  
  public Temporal.DateTime getRegisterStamp() {
      return registerStamp;
  }
  
  public Temporal.DateTime getLastLoginStamp() {
      return lastLoginStamp;
  }
  
  public String getPhone() {
      return phone;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private UserData(String id, String email, String pw, String nickname, String progress, Temporal.DateTime registerStamp, Temporal.DateTime lastLoginStamp, String phone) {
    this.id = id;
    this.email = email;
    this.pw = pw;
    this.nickname = nickname;
    this.progress = progress;
    this.registerStamp = registerStamp;
    this.lastLoginStamp = lastLoginStamp;
    this.phone = phone;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      UserData userData = (UserData) obj;
      return ObjectsCompat.equals(getId(), userData.getId()) &&
              ObjectsCompat.equals(getEmail(), userData.getEmail()) &&
              ObjectsCompat.equals(getPw(), userData.getPw()) &&
              ObjectsCompat.equals(getNickname(), userData.getNickname()) &&
              ObjectsCompat.equals(getProgress(), userData.getProgress()) &&
              ObjectsCompat.equals(getRegisterStamp(), userData.getRegisterStamp()) &&
              ObjectsCompat.equals(getLastLoginStamp(), userData.getLastLoginStamp()) &&
              ObjectsCompat.equals(getPhone(), userData.getPhone()) &&
              ObjectsCompat.equals(getCreatedAt(), userData.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), userData.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getEmail())
      .append(getPw())
      .append(getNickname())
      .append(getProgress())
      .append(getRegisterStamp())
      .append(getLastLoginStamp())
      .append(getPhone())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("UserData {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("pw=" + String.valueOf(getPw()) + ", ")
      .append("nickname=" + String.valueOf(getNickname()) + ", ")
      .append("progress=" + String.valueOf(getProgress()) + ", ")
      .append("registerStamp=" + String.valueOf(getRegisterStamp()) + ", ")
      .append("lastLoginStamp=" + String.valueOf(getLastLoginStamp()) + ", ")
      .append("phone=" + String.valueOf(getPhone()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static UserData justId(String id) {
    return new UserData(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      email,
      pw,
      nickname,
      progress,
      registerStamp,
      lastLoginStamp,
      phone);
  }
  public interface BuildStep {
    UserData build();
    BuildStep id(String id);
    BuildStep email(String email);
    BuildStep pw(String pw);
    BuildStep nickname(String nickname);
    BuildStep progress(String progress);
    BuildStep registerStamp(Temporal.DateTime registerStamp);
    BuildStep lastLoginStamp(Temporal.DateTime lastLoginStamp);
    BuildStep phone(String phone);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String email;
    private String pw;
    private String nickname;
    private String progress;
    private Temporal.DateTime registerStamp;
    private Temporal.DateTime lastLoginStamp;
    private String phone;
    @Override
     public UserData build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new UserData(
          id,
          email,
          pw,
          nickname,
          progress,
          registerStamp,
          lastLoginStamp,
          phone);
    }
    
    @Override
     public BuildStep email(String email) {
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep pw(String pw) {
        this.pw = pw;
        return this;
    }
    
    @Override
     public BuildStep nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    
    @Override
     public BuildStep progress(String progress) {
        this.progress = progress;
        return this;
    }
    
    @Override
     public BuildStep registerStamp(Temporal.DateTime registerStamp) {
        this.registerStamp = registerStamp;
        return this;
    }
    
    @Override
     public BuildStep lastLoginStamp(Temporal.DateTime lastLoginStamp) {
        this.lastLoginStamp = lastLoginStamp;
        return this;
    }
    
    @Override
     public BuildStep phone(String phone) {
        this.phone = phone;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String email, String pw, String nickname, String progress, Temporal.DateTime registerStamp, Temporal.DateTime lastLoginStamp, String phone) {
      super.id(id);
      super.email(email)
        .pw(pw)
        .nickname(nickname)
        .progress(progress)
        .registerStamp(registerStamp)
        .lastLoginStamp(lastLoginStamp)
        .phone(phone);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder pw(String pw) {
      return (CopyOfBuilder) super.pw(pw);
    }
    
    @Override
     public CopyOfBuilder nickname(String nickname) {
      return (CopyOfBuilder) super.nickname(nickname);
    }
    
    @Override
     public CopyOfBuilder progress(String progress) {
      return (CopyOfBuilder) super.progress(progress);
    }
    
    @Override
     public CopyOfBuilder registerStamp(Temporal.DateTime registerStamp) {
      return (CopyOfBuilder) super.registerStamp(registerStamp);
    }
    
    @Override
     public CopyOfBuilder lastLoginStamp(Temporal.DateTime lastLoginStamp) {
      return (CopyOfBuilder) super.lastLoginStamp(lastLoginStamp);
    }
    
    @Override
     public CopyOfBuilder phone(String phone) {
      return (CopyOfBuilder) super.phone(phone);
    }
  }
  
}
