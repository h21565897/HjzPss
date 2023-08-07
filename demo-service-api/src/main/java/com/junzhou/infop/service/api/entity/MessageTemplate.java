package com.junzhou.infop.service.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
public class MessageTemplate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer msgStatus;

    /*
    send id type
     */
    private Integer sendIdType;
    /*
    send channel id
    */
    private Integer sendChannel;

    /*
    send account id
     */
    private Integer sendAccountId;

    /**
     * template type
     */
    private Integer templateType;


    /**
     * message Type
     */
    private Integer msgType;

    private String msgContent;

    private String creator;

    private String updator;

    private Integer created;

    private Integer updated;

}
