package com.sandbox.crawler.Repository;

import com.sandbox.crawler.Model.WebPage;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Collection;

/**
 * Created by Ouasmine on 16/05/2017.
 */
public interface WebPageRepository extends CassandraRepository<WebPage> {

    Collection<WebPage> findByClient(String client);

    Collection<WebPage> findByDomaine(String domaine);

    WebPage findOneByUrl(String url);
}
