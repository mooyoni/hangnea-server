package kr.hhplus.be.server.points;

import kr.hhplus.be.server.common.enums.ChargeDirection;
import kr.hhplus.be.server.points.domain.PointsEntity;
import kr.hhplus.be.server.points.domain.PointsHistoryEntity;
import kr.hhplus.be.server.points.domain.PointsHistoryRepository;
import kr.hhplus.be.server.points.domain.PointsRepository;
import kr.hhplus.be.server.points.dto.PointChargeRequest;
import kr.hhplus.be.server.points.service.PointsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PointsServiceTest {
    @Mock
    private PointsRepository pointsRepository;

    @Mock
    private PointsHistoryRepository pointsHistoryRepository;

    @InjectMocks
    private PointsService pointsService;

    private static final Long USER_ID = 1L;
    private static final int INITIAL_POINTS = 1000;
    private static final int CHARGE_AMOUNT = 500;
    private static final int USE_AMOUNT = 500;

    private PointsEntity pointsEntity;

    @BeforeEach
    void setUp() {
        pointsEntity = new PointsEntity(1L, USER_ID, INITIAL_POINTS);
    }

    @Test
    @DisplayName("최초 충전 유저의 포인트 충전 테스트")
    void chargePointsFirst(){
        //given
        PointChargeRequest request = new PointChargeRequest(USER_ID,CHARGE_AMOUNT, ChargeDirection.PLUS);
        when(pointsRepository.findByUserId(USER_ID)).thenReturn(Optional.empty());
        when(pointsRepository.save(any(PointsEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));
        //when
        pointsService.chargePoints(request);
        //then
        ArgumentCaptor<PointsEntity> pointsCaptor = ArgumentCaptor.forClass(PointsEntity.class);
        verify(pointsRepository).save(pointsCaptor.capture());
        PointsEntity savedPoints = pointsCaptor.getValue();

        assertEquals(USER_ID, savedPoints.getUserId());
        assertEquals(CHARGE_AMOUNT, savedPoints.getPoints());

        ArgumentCaptor<PointsHistoryEntity> historyCaptor = ArgumentCaptor.forClass(PointsHistoryEntity.class);
        verify(pointsHistoryRepository).save(historyCaptor.capture());
        PointsHistoryEntity savedHistory = historyCaptor.getValue();

        assertEquals(USER_ID, savedHistory.getUserId());
        assertEquals(CHARGE_AMOUNT, savedHistory.getAmount());
        assertEquals(ChargeDirection.PLUS, savedHistory.getChargeDirection());
        assertNotNull(savedHistory.getChargedAt());

    }

    @Test
    @DisplayName("충전 이력이 있는 유저의 포인트 충전 테스트")
    void chargePointsNotFirst(){
        //given
        PointChargeRequest request = new PointChargeRequest(USER_ID, CHARGE_AMOUNT, ChargeDirection.PLUS);
        when(pointsRepository.findByUserId(USER_ID)).thenReturn(Optional.of(pointsEntity));
        when(pointsRepository.save(any(PointsEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //when
        pointsService.chargePoints(request);

        //then
        ArgumentCaptor<PointsEntity> pointsCaptor = ArgumentCaptor.forClass(PointsEntity.class);
        verify(pointsRepository).save(pointsCaptor.capture());
        PointsEntity savedPoints = pointsCaptor.getValue();

        assertEquals(USER_ID, savedPoints.getUserId());
        assertEquals(INITIAL_POINTS + CHARGE_AMOUNT, savedPoints.getPoints());

        verify(pointsHistoryRepository).save(any(PointsHistoryEntity.class));
    }
}
