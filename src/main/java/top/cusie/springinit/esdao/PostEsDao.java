package top.cusie.springinit.esdao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import top.cusie.springinit.model.dto.post.PostEsDTO;

import java.util.List;

/**
 * 帖子 ES 操作
 *
 */
public interface PostEsDao extends ElasticsearchRepository<PostEsDTO, Long> {

    List<PostEsDTO> findByUserId(Long userId);
}