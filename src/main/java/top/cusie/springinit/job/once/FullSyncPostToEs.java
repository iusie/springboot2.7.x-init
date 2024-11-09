package top.cusie.springinit.job.once;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import top.cusie.springinit.esdao.PostEsDao;
import top.cusie.springinit.model.dto.post.PostEsDTO;
import top.cusie.springinit.model.entity.Post;
import top.cusie.springinit.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全量同步帖子到 es
 *
 */
// todo 取消注释开启任务
//@Component
@RequiredArgsConstructor
@Slf4j
public class FullSyncPostToEs implements CommandLineRunner {

    private final PostService postService;

    private final PostEsDao postEsDao;

    @Override
    public void run(String... args) {
        List<Post> postList = postService.list();
        if (CollUtil.isEmpty(postList)) {
            return;
        }
        List<PostEsDTO> postEsDTOList = postList.stream().map(PostEsDTO::objToDto).collect(Collectors.toList());
        final int pageSize = 500;
        int total = postEsDTOList.size();
        log.info("FullSyncPostToEs start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            log.info("sync from {} to {}", i, end);
            postEsDao.saveAll(postEsDTOList.subList(i, end));
        }
        log.info("FullSyncPostToEs end, total {}", total);
    }
}
