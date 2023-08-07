package com.junzhou.infop.service.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ChannelAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Account Name
     */
    private String name;

    /**
     * Sending Channel
     * com.junzhou.infop.pipeline.enums.ChannelCode
     */
    private Integer sendChannel;

    /**
     * Account Configuration
     */
    private String accountConfig;

    /**
     *
     */
    private Integer isDeleted;

    /**
     *
     */
    private String creator;

    /**
     *
     */
    private Integer created;

    /**
     *
     */
    private Integer updated;
}
