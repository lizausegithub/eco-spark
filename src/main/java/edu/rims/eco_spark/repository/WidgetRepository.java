package edu.rims.eco_spark.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.eco_spark.constant.WidgetStatus;
import edu.rims.eco_spark.entity.Widget;

public interface WidgetRepository extends JpaRepository<Widget, String> {
    List<Widget> findByWidgetStatus(WidgetStatus
     widgetStatus, Sort sort);
}
