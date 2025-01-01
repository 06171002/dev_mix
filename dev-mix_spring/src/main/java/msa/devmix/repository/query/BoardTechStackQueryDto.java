package msa.devmix.repository.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardTechStackQueryDto {

    private Long boardId;
    private String techStackImageUrl;
}
