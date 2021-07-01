package pl.com.wpi.task1.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PollVote
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Temperature {
    @Id
    private Integer localization;
    private Integer value;

}